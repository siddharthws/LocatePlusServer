package locateplusserver.services

import grails.gorm.transactions.Transactional
import locateplusserver.domains.User
import locateplusserver.ApiException
import locateplusserver.Role
import locateplusserver.Constants

@Transactional
class AuthService {
    // ----------------------- Dependencies ---------------------------//
    def userService
    def updateService

    // ----------------------- Public methods ---------------------------//

    // Method to register a user using IMEI and return its object
    User register(String imei ,String user_role ) {
        // Check if user with this imei already existss
        def user = userService.getByImei(imei)

            updateService.intializeUpdates()

        if(user)
        {
            return user
        }

        Role role = user_role as Role

            // Add new admin if not found

                if(role==Role.ADMIN) {
                    String name = generateName()
                    user = new User(imei: imei, name: name, role: Role.ADMIN)
                    user.save(flush: true, failOnError: true)
                }
                else
                {
                    String name = generateName()
                    user = new User(imei: imei, name: name, role: Role.USER)
                    user.save(flush: true, failOnError: true)
                }

        user
    }
    // Method to check if imei is present or not
    def checkImei(String imei){

        // If imei is null throw exception
        if (!imei) {
            throw new ApiException("Invalid registration request", Constants.HttpCodes.BAD_REQUEST)
        }

    }

    // ----------------------- Private methods ---------------------------//
    //Method to generate id and username
    private def generateName() {
        int randomNum = Math.abs(new Random().nextInt() % 100) + 1
        return "LocPUser" + randomNum
    }


}
