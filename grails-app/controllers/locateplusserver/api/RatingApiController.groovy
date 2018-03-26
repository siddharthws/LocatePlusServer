package locateplusserver.api

import grails.converters.JSON
import locateplusserver.domains.User
import locateplusserver.domains.Admin
import locateplusserver.domains.Rating
import locateplusserver.ApiException

class RatingApiController {
    // ----------------------- Dependencies ---------------------------//
    def authService
    def userService
    def adminService

    // ----------------------- Public APIs ---------------------------//

    def photoInappropriate(){





    }

    def addOverallRating(){

        //get data from request
        def overAllRating = request.JSON.rating
        def placeId = request.JSON.placeId
        def imei = request.getHeader("imei")

        // get user object by imei
        def user = userService.getByImei(imei)

        if(!user) {
            throw new ApiException("Not registered", Constants.HttpCodes.BAD_REQUEST)
        }

        /// update RAATINGggggggggggggggggggggggggggggggggggggggggggssssssssssss
        def place = userService.getPlaceById(placeId)

        Rating rating = new Rating(
                overAllRating: overAllRating,
                owner: user
        )


        // associate review to a place
        place.addToRatings(rating)

        //save place object
        place.save(flush: true, failOnError: true)

        def resp = [success: true]
        render resp as JSON

    }




    // ----------------------- Private APIs ---------------------------//
}
