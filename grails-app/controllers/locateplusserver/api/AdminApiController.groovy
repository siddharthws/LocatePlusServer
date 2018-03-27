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

        def newCategory = request.JSON.category


           def category = new Category(
                    name : newCategory
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

    def addFacility(){

        def newFacility = request.JSON.facility

        log.error("hello"+newFacility)
        // Create new facility object based on facility provided
        def facility = new Facility(
                name : newFacility
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
