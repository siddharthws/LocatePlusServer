package locateplusserver.services

import grails.gorm.transactions.Transactional
import locateplusserver.domains.User

@Transactional
class AuthService {
    // ----------------------- Dependencies ---------------------------//
    def userService

    // ----------------------- Public methods ---------------------------//
    // Method to register a user using IMEI and return its object
    User register(String imei) {
        // Check if user with this imei already exists
        def user = userService.getByImei(imei)

        // Add new user if not found
        if (!user) {
            String name = generateName()
            user = new User(imei: imei, name: name)
            user.save(flush: true, failOnError: true)
        }

        user
    }

    // ----------------------- Private methods ---------------------------//
    private def generateName() {
        int randomNum = Math.abs(new Random().nextInt() % 100) + 1
        return "LocPUser" + randomNum
    }
}
