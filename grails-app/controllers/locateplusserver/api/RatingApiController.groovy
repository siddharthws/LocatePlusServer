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

        def place = userService.getPlaceById(placeId)

        def ratingPresent = ratingService.getRatingByUserAndPlace(user,place)

        if(!ratingPresent){

            ratingService.createNewRating("overAllRating",place,user,rating)

        } else{

            ratingPresent.overAllRating = rating

            ratingPresent.save(flush: true, failOnError: true)

        }


        def resp = [success: true]
        render resp as JSON

    }




    // ----------------------- Private APIs ---------------------------//
}
