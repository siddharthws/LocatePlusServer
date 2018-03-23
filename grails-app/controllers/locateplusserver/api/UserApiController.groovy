package locateplusserver.api

import grails.converters.JSON
import locateplusserver.domains.Place
import locateplusserver.domains.Rating
import locateplusserver.domains.Category
import locateplusserver.domains.Facility
import locateplusserver.domains.Review
import locateplusserver.domains.Place
import locateplusserver.domains.User
import locateplusserver.ApiException
import locateplusserver.Constants
import org.grails.web.json.JSONArray

class UserApiController {
    // ----------------------- Dependencies ---------------------------//
    def userService
    def authService
    def updateService

    // ----------------------- Public APIs ---------------------------//
    // Api to save place entered in database
    def addPlace() {

        def imei = request.getHeader("imei")

        // check if imei is present
        authService.checkImei(imei)

        // get user object by imei number
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
        def description = request.JSON.description

        address.replaceAll("\\n", "")

        def cat = request.JSON.category
        def facilitiesJson = request.JSON.facilities

        //get category object by Id of category
        def category = userService.getCategoryById(cat)

        //creating a new pace object
        Place place = new Place(
                name        : name,
                latitude    : lat,
                longitude   : lng,
                address     : address,
                category    : category,
                description : description
        )

        // Iterate over facilities and get each facility object
        facilitiesJson.each { facility ->

            def id = facility.id

            def facilityPresent =  userService.getFacilityById(id)

          if(facilityPresent)
             {
                 // Add facility to the place
                 place.addToFacilities(facilityPresent)
             }


        }
        // set owner as user
        user.addToPlaces(place)

        // save place
        user.save(flush: true, failOnError: true)

        //Update place status
        updateService.updatePlaceStatus()

        // return response
        def resp = [sucess: true]
        render resp as JSON
    }

    // Return all Places that are not removed
    def getPlaces() {

        def imei = request.getHeader("imei")

        // check if imei is present
        authService.checkImei(imei)

        log.error("getplaces request by :"+imei)

        // get user object based on imei
        User user = userService.getByImei(imei)

        if(!user)
        {
            throw new ApiException("Not Registered", Constants.HttpCodes.BAD_REQUEST)
        }

        //get all places list
        def placeList = userService.getAllPlaces()

        def places = new JSONArray()

        // Create Place Json objects for each place and add to response
        placeList.each { member ->
            places.add(locateplusserver.Marshaller.serializePlace(member))
        }

        def resp = [markers:places]

        render resp as JSON
    }

    // Return Facilities and categories at app startup
    def getFC(){

        def resp
        def imei = request.getHeader("imei")

        // check if imei is present
        authService.checkImei(imei)

        log.error("FC request by :"+imei)

        def user = userService.getByImei(imei)

        if(!user)
        {
            throw new ApiException("Not Registered", Constants.HttpCodes.BAD_REQUEST)
        }


            def categories = getCategories()
            def facilities = getFacilities()

            resp = [categories:categories ,facilities: facilities]

            user.save(flush: true, failOnError: true)

        // return response
        render resp as JSON
    }


     // Return all categories in database
    def getCategories() {
        // get all categories list from database
        def categoriesList = userService.getAllCategories()

        def categories = new JSONArray()

        // Iterate over categories list and return serialized categories list
        categoriesList.each { member ->
            categories.add(locateplusserver.Marshaller.serializeCategory(member))
            }

        categories
    }

    // Return all facilities in database
    def getFacilities() {
        // get all facilities list from database
        def facilitiesList = userService.getAllFacilities()

        def facilities = new JSONArray()

        // Iterate over facilities list and return serialized facilities list
        facilitiesList.each { member ->
            facilities.add(locateplusserver.Marshaller.serializeFacility(member))
        }

        facilities
    }

    def addRatings() {
        // get data from request
        def rating = request.JSON.rating
        def placeId = request.JSON.placeId
        def imei = request.getHeader("imei")

        // check if imei is present
        authService.checkImei(imei)

        // Get user object by imei
        def user = userService.getByImei(imei)

        if(!user) {
            throw new ApiException("Not Registered", Constants.HttpCodes.BAD_REQUEST)
        }

        //get place object by place id
        def place = userService.getPlaceById(placeId)

        //Create new rating object with user as its owner
        Rating ratings = new Rating(
                rating: rating,
                owner : user
        )

        // associate rating to a place
        place.addToRatings(ratings)

        //save place object
        place.save(flush: true, failOnError: true)

        // return response
        def resp = [sucess: true]
        render resp as JSON
    }

    def addReviews() {
        //get data from request
        def review = request.JSON.review
        def placeId = request.JSON.placeId
        def imei = request.getHeader("imei")

        // get user object by imei
        def user = userService.getByImei(imei)

        if(!user) {
            throw new ApiException("Not registered", Constants.HttpCodes.BAD_REQUEST)
        }

        def place = userService.getPlaceById(placeId)

        Review reviews = new Review(
                review: review,
                owner: user
        )

        // associate review to a place
        place.addToReviews(reviews)

        //update place Review Status
        updateService.updateReviewStatus(place)

        //save place object
        place.save(flush: true, failOnError: true)

        //return response
        def resp = [sucess: true]
        render resp as JSON
    }

    def getReviews() {
        //get data from request
        def placeId = request.JSON.placeId
        def imei = request.getHeader("imei")

        // get user object by imei
        def user = userService.getByImei(imei)

        log.error("get Reviews request by :"+imei)

        if(!user) {
            throw new ApiException("Not registered", Constants.HttpCodes.BAD_REQUEST)
        }

        def reviewList = userService.getReviewsById(placeId)

        def reviews = new JSONArray()

        // Iterate over facilities list and return serialized facilities list
        reviewList.each { member ->
            reviews.add(member.review)
        }


        def resp = [placeId : placeId , reviews :reviews]

        //return response

        render resp as JSON
    }

    def getrpStatus(){
        //get data from request
        def placeId = request.JSON.placeId
        def imei = request.getHeader("imei")

        log.error("getrpStatus request by :"+imei)

        // get user object by imei
        def user = userService.getByImei(imei)

        if(!user) {
            throw new ApiException("Not registered", Constants.HttpCodes.BAD_REQUEST)
        }

        // get place by ID
        def place = userService.getPlaceById(placeId)

        // get review and photo update status of place
        def reviewStatus = place.reviewStatus

        def photoStatus = place.photoStatus

        //save place object
        place.save(flush: true, failOnError: true)


        def resp = [reviewResponse : reviewStatus , photoResponse : photoStatus]

        //return response
        render resp as JSON

    }
}