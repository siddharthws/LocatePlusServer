package locateplusserver.api

import grails.converters.JSON
import locateplusserver.ApiException
import locateplusserver.Constants
import locateplusserver.domains.Facility
import locateplusserver.domains.Category
import locateplusserver.domains.Place
import locateplusserver.Role

class AdminApiController {


    // ----------------------- Dependencies ---------------------------//
    def userService
    def authService
    // ----------------------- Public APIs ---------------------------//

    // API to add a category
    def addCategory() {

        //get imei of user
        def imei = request.getHeader("imei")

        // check if imei is present
        authService.checkImei(imei)

        def user = userService.getByImei(imei)

        if(!user)
        {
            throw new ApiException("Not Registered", Constants.HttpCodes.BAD_REQUEST)
        }

        if(user.role==Role.USER)
        {
            throw new ApiException("Not Authorized", Constants.HttpCodes.UNAUTHORIZED)
        }

        def categoriesJson = request.JSON



           def category = new Category(
                    name : categoriesJson.category
            )

        // save category in database .
        category.save(flush: true, failOnError: true)

        // update the status for all users
        userService.updateAllUserStatus()

        def resp = [success: true]
        render resp as JSON
    }

    // API to remove a place
    def removePlace() {

        //get imei of user
        def imei = request.getHeader("imei")

        // check if imei is present
        authService.checkImei(imei)

        // Get user by imei
        def user = userService.getByImei(imei)

        // Check if user is registered
        if(!user)
        {
            throw new ApiException("Not Registered", Constants.HttpCodes.BAD_REQUEST)
        }


        if(user.role==Role.USER)
        {
            throw new ApiException("Not Authorized", Constants.HttpCodes.UNAUTHORIZED)
        }

        def id = request.JSON.placeId

        // get Place by id
        def place = Place.findById(id)
        place.isRemoved = true
        place.save(flush: true,failOnError: true)

        // update the status for all users
        userService.updateAllUserStatus()

        def resp = [success: true]
        render resp as JSON

    }

    def addFacilities(){

        //get data from request
        def imei = request.getHeader("imei")
        def facilitiesJson = request.JSON

        // check if imei is present
        authService.checkImei(imei)

        // Get user by Imei
        def user = userService.getByImei(imei)

        // Check if user is registered
        if(!user)
        {
            throw new ApiException("Not Registered", Constants.HttpCodes.BAD_REQUEST)
        }

        //check if user is admin
        if(user.role==Role.USER)
        {
            throw new ApiException("Not Authorized", Constants.HttpCodes.UNAUTHORIZED)
        }

        // Create new facility object based on facility provided
        def facility = new Facility(
                name : facilitiesJson.facility
        )

        // save facility
        facility.save(flush: true,failOnError: true)

        // update the status for all users
        userService.updateAllUserStatus()

        //return response
        def resp = [success: true]
        render resp as JSON


    }

    // ----------------------- Private APIs ---------------------------//
}
