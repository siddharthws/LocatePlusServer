package locateplusserver.api

import grails.converters.JSON
import locateplusserver.domains.Place
import locateplusserver.domains.Rating
import locateplusserver.domains.Category
import locateplusserver.domains.Facility
import locateplusserver.domains.User
import locateplusserver.ApiException
import locateplusserver.Constants
import org.grails.web.json.JSONArray

class UserApiController {
    // ----------------------- Dependencies ---------------------------//
    def userService
    def authService

    // ----------------------- Public APIs ---------------------------//
    // Api to save place entered in database
    def addPlace() {

        def imei = request.getHeader("imei")

        // check if imei is present
        authService.checkImei(imei)

        def user = userService.getByImei(imei)

        //get data from JSON
        def name = request.JSON.name
        def lat = request.JSON.latitude
        def lng = request.JSON.longitude
        def address = request.JSON.address
        def cat = request.JSON.category
        def facilitiesJson = request.JSON.facilities

        def category = userService.getCategoryById(cat)

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
                category    : category
        )

        facilitiesJson.each { facility ->

            def id = facility.id

            def facilityPresent =  userService.getFacilityById(id)

          if(facilityPresent)
             {
                 place.addToFacilities(facilityPresent)
             }


        }

        //save place
        // set owner as user
        user.addToPlaces(place)

        // save place
        user.save(flush: true, failOnError: true)

        //send response
        def resp = [sucess: true]
        render resp as JSON
    }

    def getPlaces() {

        def imei = request.getHeader("imei")

        // check if imei is present
        authService.checkImei(imei)

        User user = userService.getByImei(imei)

        def placeList = userService.getAllPlaces()
        def resp = new JSONArray()

        // Create Place Json objects
        placeList.each { member ->
            resp.add(locateplusserver.Marshaller.serializePlace(member))
        }

        render resp as JSON
    }
    def getFC(){

        def resp
        def imei = request.getHeader("imei")

        // check if imei is present
        authService.checkImei(imei)

        log.error("FC request by :"+imei)

        def user = userService.getByImei(imei)


            def categories = getCategories()
            def facilities = getFacilities()

            resp = [categories:categories ,facilities: facilities]

        // update status for user
            user.updateRequired = false
            user.save(flush: true, failOnError: true)

        render resp as JSON
    }



    def getCategories() {
        def categoriesList = userService.getAllCategories()

        def categories = new JSONArray()

        categoriesList.each { member ->
            categories.add(locateplusserver.Marshaller.serializeCategory(member))
            }

        categories
    }

    def getFacilities() {
        def facilitiesList = userService.getAllFacilities()

        def facilities = new JSONArray()

        facilitiesList.each { member ->
            facilities.add(locateplusserver.Marshaller.serializeFacility(member))
        }

        def resp = [facilities:facilities]

        facilities
    }

    def addRatings() {
        def imei = request.getHeader("imei")

        // check if imei is present
        authService.checkImei(imei)

        def user = userService.getByImei(imei)


        def rating = request.JSON.rating
        def placeId = request.JSON.placeId

        def place = userService.getPlaceById(placeId)
        if(!place) {
            throw new ApiException("Place does not Exist", Constants.HttpCodes.BAD_REQUEST)
        }

        Rating ratin = new Rating(
                rating: rating,
                owner : user
        )

        place.addToRatings(ratin)
        place.save(flush: true, failOnError: true)

        def resp = [sucess: true]
        render resp as JSON
    }
}