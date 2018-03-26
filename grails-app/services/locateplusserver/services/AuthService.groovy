package locateplusserver.services

import grails.gorm.transactions.Transactional
import locateplusserver.domains.User
import locateplusserver.domains.Admin
import locateplusserver.ApiException
import locateplusserver.Role
import locateplusserver.Constants

@Transactional
class AuthService {
    // ----------------------- Dependencies ---------------------------//
    def userService
    def updateService
    def authService
    def adminService

    // ----------------------- Public methods ---------------------------//

    // Method to register a user using IMEI and return its object
    User registerUser(String imei) {
        // Check if user with this imei already existss
        def user = userService.getByImei(imei)

        updateService.intializeUpdates()

        if(user)
        {
            return user
        }

        String name = generateName()
        user = new User(imei: imei, name: name)
        user.save(flush: true, failOnError: true)

        user
    }

    // Method to register a user using IMEI and return its object
    Admin registerAdmin(String username) {
        // Check if user with this username already exists
        def admin = adminService.getByName(username)

        updateService.intializeUpdates()

        if(admin)
        {
            return admin
        }

        String password = generateName()
        admin = new Admin(userName: username, password: password)
        admin.save(flush: true, failOnError: true)

        admin
    }
    // Method to check if imei is present or not
    def checkImei(String imei){

        // If imei is null throw exception
        if (!imei) {
            throw new ApiException("Invalid registration request", Constants.HttpCodes.BAD_REQUEST)
        }

    }

    def checkUsername(String name){

        // If name is null throw exception
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
