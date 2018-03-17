package locateplusserver.api

import grails.converters.JSON
import locateplusserver.domains.PlaceDetails
import locateplusserver.domains.Category

class UserApiController {
    // ----------------------- Public APIs ---------------------------//
    // Api to save place entered in database
    def addPlace() {
        //get data from JSON
        def lat = request.JSON.latitude
        def lng = request.JSON.longitude
        def name = request.JSON.name
        def address = request.JSON.address

        Category category = new Category(
                name: request.JSON.category
        )

        //creating a new pace object
        PlaceDetails place = new PlaceDetails(
                name        : name,
                latitude    : lat,
                longitude   : lng,
                address     : address,
                category    : category
        )


        //save place
        place.save(flush: true, failOnError: true)

        //send response
        def resp = [sucess: true]
        render resp as JSON
    }

    def getPlace() {
        // Find place data by id
        def place = PlaceDetails.findById(1)

        // Create Place Json object
        def placeJson = [
                name        : place.name,
                latitude    : place.latitude,
                longitude   : place.longitude,
                address     : place.address,
                category    : place.category
        ]

        def resp = placeJson
        render resp as JSON
    }
}