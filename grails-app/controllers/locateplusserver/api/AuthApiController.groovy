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
        String imei = request.getHeader("imei")
        if (!imei) {
            throw new ApiException("Invalid registration request", Constants.HttpCodes.BAD_REQUEST)
        }

        // Register user
        User user = authService.register(imei)

        // Return user details
        def resp = userService.toJson(user)
        render resp as JSON
    }

    // ----------------------- Private APIs ---------------------------//
}
