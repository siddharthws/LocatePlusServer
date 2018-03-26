package locateplusserver.api

import grails.converters.JSON
import locateplusserver.ApiException
import locateplusserver.Constants
import locateplusserver.domains.Facility
import locateplusserver.domains.Category
import locateplusserver.domains.Place

class AdminApiController {


    // ----------------------- Dependencies ---------------------------//
    def userService
    def authService
    def updateService
    def adminService
    // ----------------------- Public APIs ---------------------------//

    // API to add a category
    def addCategory() {

        //get username of user
        def username = request.getHeader("username")

        // check if username field is present
        adminService.checkUsername(username)

        def admin = adminService.getByName(username)

        if(!admin)
        {
            throw new ApiException("Admin Not Registered", Constants.HttpCodes.BAD_REQUEST)
        }

        def categoriesJson = request.JSON


           def category = new Category(
                    name : categoriesJson.category
            )

        // save category in database .
        category.save(flush: true, failOnError: true)

        // update the FC status for all users
        updateService.updateFcStatus()

        def resp = [success: true]
        render resp as JSON
    }

    // API to remove a place
    def removePlace() {

       /* //get username of user
        def username = request.getHeader("username")

        // check if username field is present
        authService.checkUsername(username)

        def admin = adminService.getByName(username)

        if(!admin)
        {
            throw new ApiException("Admin Not Registered", Constants.HttpCodes.BAD_REQUEST)
        }*/

        def id = request.JSON.placeId

        // get Place by id
        def place = Place.findById(id)
        place.isRemoved = true
        place.save(flush: true,failOnError: true)

        // update the Place status for all users
        updateService.updatePlaceStatus()

        def resp = [success: true]
        render resp as JSON

    }

    def addFacilities(){

        //get username of user
        def username = request.getHeader("username")

        // check if username field is present
        authService.checkUsername(username)

        def admin = adminService.getByName(username)

        if(!admin)
        {
            throw new ApiException("Admin Not Registered", Constants.HttpCodes.BAD_REQUEST)
        }

        // Create new facility object based on facility provided
        def facility = new Facility(
                name : facilitiesJson.facility
        )

        // save facility
        facility.save(flush: true,failOnError: true)

        // update the Place status for all users
        updateService.updateFcStatus()

        //return response
        def resp = [success: true]
        render resp as JSON


    }

    // ----------------------- Private APIs ---------------------------//
}
