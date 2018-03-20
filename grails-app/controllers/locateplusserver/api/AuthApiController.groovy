package locateplusserver.api

import grails.converters.JSON
import locateplusserver.ApiException
import locateplusserver.Constants
import locateplusserver.domains.User

class AuthApiController {
    // ----------------------- Dependencies ---------------------------//
    def authService
    def userService

    // ----------------------- Public APIs ---------------------------//
    // API to register a user and return user details
    def register() {
        // Get IMEI from request
        def role = request.JSON.role
        String imei = request.getHeader("imei")

        // check if imei is present
        authService.checkImei(imei)

        log.error("Registration request by :"+imei)
        // Register user
        User user = authService.register(imei, role)

        // Return user details
        def resp = userService.toJson(user)
        render resp as JSON
    }

    // ----------------------- Private APIs ---------------------------//
}
