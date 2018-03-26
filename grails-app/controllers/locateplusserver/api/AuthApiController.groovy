package locateplusserver.api

import grails.converters.JSON
import locateplusserver.domains.User
import locateplusserver.domains.Admin

class AuthApiController {
    // ----------------------- Dependencies ---------------------------//
    def authService
    def userService
    def adminService

    // ----------------------- Public APIs ---------------------------//
    // API to register a user and return user details
    def registerUser() {
        // Get IMEI from request
        String imei = request.getHeader("imei")

        // check if imei is present
        authService.checkImei(imei)

        log.error("Registration request by :"+imei)
        // Register user
        User user = authService.registerUser(imei)

        // Return user details
        def resp = userService.toJsonUser(user)
        render resp as JSON
    }

    def registerAdmin() {

        // Get username from request
        String username = request.getHeader("username")

        // check if username is present
        adminService.checkUsername(username)

        log.error("Admin Registration request by :"+username)
        // Register admin
        Admin admin = authService.registerAdmin(username)

        // Return admin details
        def resp = adminService.toJsonAdmin(admin)

        render resp as JSON
    }

    // ----------------------- Private APIs ---------------------------//
}
