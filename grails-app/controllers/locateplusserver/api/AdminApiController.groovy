package locateplusserver.api

import grails.converters.JSON
import locateplusserver.ApiException
import locateplusserver.Constants
import locateplusserver.domains.User
import locateplusserver.domains.Category
import locateplusserver.domains.Place
import locateplusserver.Role

class AdminApiController {


    // ----------------------- Dependencies ---------------------------//
    def userService
    // ----------------------- Public APIs ---------------------------//

    // API to add a category
    def addCategory() {

        //get imei of user
        def imei = request.getHeader("imei")
        def user = userService.getByImei(imei)

        if(!user)
        {
            throw new ApiException("Not Registered", Constants.HttpCodes.BAD_REQUEST)
        }

        if(user.role==Role.USER)
        {
            throw new ApiException("Not Authorized", Constants.HttpCodes.BAD_REQUEST)
        }

        def categoriesJson = request.JSON



           def category = new Category(
                    name : categoriesJson.category
            )

        // save category in database .
        category.save(flush: true, failOnError: true)

        def resp = [success: true]
        render resp as JSON
    }

    // API to remove a place
    def removePlace() {

        //get imei of user
        def imei = request.getHeader("imei")
        def user = userService.getByImei(imei)

        if(!user)
        {
            throw new ApiException("Not Registered", Constants.HttpCodes.BAD_REQUEST)
        }

        if(user.role==Role.USER)
        {
            throw new ApiException("Not Authorized", Constants.HttpCodes.BAD_REQUEST)
        }

        def id = request.JSON.placeId

        // get Place by id
        def place = Place.findById(id)
        place.isRemoved = true
        place.save(flush: true,failOnError: true)

        def resp = [success: true]
        render resp as JSON

    }

    // ----------------------- Private APIs ---------------------------//
}
