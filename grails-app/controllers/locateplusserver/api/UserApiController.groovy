package locateplusserver.api

import grails.converters.JSON
import locateplusserver.domains.Place
import locateplusserver.domains.Category
import locateplusserver.domains.User
import locateplusserver.ApiException
import locateplusserver.Constants
import org.grails.web.json.JSONArray

class UserApiController {
    // ----------------------- Dependencies ---------------------------//
    def userService

    // ----------------------- Public APIs ---------------------------//
    // Api to save place entered in database
    def addPlace() {

        def imei = request.getHeader("imei")

        def user = userService.getByImei(imei)

        if(!user)
        {
            throw new ApiException("Not Registered", Constants.HttpCodes.BAD_REQUEST)
        }

        //get data from JSON
        def name = request.JSON.name
        def lat = request.JSON.latitude
        def lng = request.JSON.longitude
        def address = request.JSON.address
        def cat = request.JSON.category

        def category = userService.getCategory(cat)

        if(!category)
        {
            throw new ApiException("Invalid Category", Constants.HttpCodes.BAD_REQUEST)
        }

        //creating a new pace object
        Place place = new Place(
                name        : name,
                latitude    : lat,
                longitude   : lng,
                address     : address,
                user        : user,
                category    :category
        )

        //save place
        place.save(flush: true, failOnError: true)

        //send response
        def resp = [sucess: true]
        render resp as JSON
    }

    def getPlaces() {

        def imei = request.getHeader("imei")

        User user = userService.getByImei(imei)

        if(!user)
        {
            throw new ApiException("Not Registered", Constants.HttpCodes.BAD_REQUEST)
        }

        def placeList = userService.getAllPlaces()
        def resp = new JSONArray()

        // Create Place Json objects
        placeList.each { member ->
            resp.add(locateplusserver.Marshaller.serializePlace(member))
        }

        render resp as JSON
    }


}