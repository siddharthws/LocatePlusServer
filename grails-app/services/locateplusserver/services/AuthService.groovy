package locateplusserver.services

import grails.gorm.transactions.Transactional
import locateplusserver.domains.User
import locateplusserver.ApiException
import locateplusserver.Role

@Transactional
class AuthService {
    // ----------------------- Dependencies ---------------------------//
    def userService

    // ----------------------- Public methods ---------------------------//
    // Method to register a user using IMEI and return its object
    User register(String imei ,String user_role ) {
        // Check if user with this imei already existss
        def user = userService.getByImei(imei)

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

    def checkImei(String imei){

        if (!imei) {
            throw new ApiException("Invalid registration request", Constants.HttpCodes.BAD_REQUEST)
        }

    }

    // ----------------------- Private methods ---------------------------//
    private def generateName() {
        int randomNum = Math.abs(new Random().nextInt() % 100) + 1
        return "LocPUser" + randomNum
    }


}
