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
        def cat = request.JSON.category

        Category category = new Category()
        category.name = cat

        place.category = category

        //creating a new pace object
        PlaceDetails place = new PlaceDetails(
                name        : name,
                latitude    : lat,
                longitude   : lng,
                address     : address,
                category    : cat
        )


        //save place
        place.save(flush: true, failOnError: true)

        //send response
        def resp = [sucess: true]
        render resp as JSON
    }

    def getPlace(PlaceDetails place) {
        // Find place data by id
        def p = PlaceDetails.findAllByid(place.id)

        // Create Place Json object
        def placeJson = [
                id          : p.id,
                name        : p.name,
                latitude    : p.lat,
                longitude   : p.lng,
                address     : p.address,
                category    : p.category
        ]

        def resp = placeJson
        render resp as JSON
    }
}