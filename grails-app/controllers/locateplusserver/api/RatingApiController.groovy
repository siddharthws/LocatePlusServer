package locateplusserver.api

import grails.converters.JSON
import locateplusserver.domains.User
import locateplusserver.domains.Place
import locateplusserver.Constants
import locateplusserver.domains.Rating
import locateplusserver.ApiException

class RatingApiController {
    // ----------------------- Dependencies ---------------------------//
    def authService
    def userService
    def adminService
    def ratingService

    // ----------------------- Public APIs ---------------------------//

    def photoRating(){

        def imei = request.getHeader("imei")
        def placeId = request.JSON.placeId

        // get user object by imei
        def user = userService.getByImei(imei)

               if(!user) {
            throw new ApiException("Not registered", Constants.HttpCodes.BAD_REQUEST)
        }


        def photoList = request.JSON.photos

        log.error("photod"+photoList)
        log.error("photod"+request.JSON)

        Place place = userService.getPlaceById(placeId)

        def photoRating = []

        photoList.each { member ->

            def uuid = member.photoUuid
            def rating = member.photoRate

            photoRating.add(uuid: uuid, rating: rating)

        }

        Integer rating = ratingService.photoRatingAlgorithm(photoRating)

        def ratingPresent = ratingService.getRatingByUserAndPlace(user,place)

        if(!ratingPresent){

            ratingService.createNewRating("photoRating",place,user,rating)

            } else{

            ratingPresent.photoRating = rating

            ratingPresent.save(flush: true, failOnError: true)

        }


        def resp = [success: true]
        render resp as JSON

    }

    def infoRating(){

        def imei = request.getHeader("imei")

        // get user object by imei
        def user = userService.getByImei(imei)

        if(!user) {
            throw new ApiException("Not registered", Constants.HttpCodes.BAD_REQUEST)
        }

        def placeId = request.JSON.placeId
        def nameRating = request.JSON.name
        def categoryRating = request.JSON.category
        def addressRating = request.JSON.address

        Place place = userService.getPlaceById(placeId)

        def infoRating = []

        infoRating.add(rating:nameRating)
        infoRating.add(rating:categoryRating)
        infoRating.add(rating:addressRating)

        Integer rating =  ratingService.infoRatingAlgorithm(infoRating)

        def ratingPresent = ratingService.getRatingByUserAndPlace(user,place)

        if(!ratingPresent){

            ratingService.createNewRating("infoRating",place,user,rating)

        } else{

            ratingPresent.infoRating = rating

            ratingPresent.save(flush: true, failOnError: true)

        }


        def resp = [success: true]
        render resp as JSON

    }

    def facilitiesRating(){

        def imei = request.getHeader("imei")

        // get user object by imei
        def user = userService.getByImei(imei)

        if(!user) {
            throw new ApiException("Not registered", Constants.HttpCodes.BAD_REQUEST)
        }

        def placeId = request.JSON.placeId

        def facilitiesList = request.JSON.facilities

        Place place = userService.getPlaceById(placeId)

        def facilityRating = []

        facilitiesList.each{member->

            def id = member.faciltyId
            def rating = member.facilityRate

            facilityRating.add(id:id, rating:rating)
        }

        Integer rating =  ratingService.facilitiesRatingAlgorithm(facilityRating)

        def ratingPresent = ratingService.getRatingByUserAndPlace(user,place)

        if(!ratingPresent){

            ratingService.createNewRating("facilitiesRating",place,user,rating)

        } else{

            ratingPresent.facilitiesRating = rating

            ratingPresent.save(flush: true, failOnError: true)

        }

        def resp = [success: true]
        render resp as JSON

    }

    def overallRating(){

        //get data from request
        def ratings = request.JSON.rating
        def placeId = request.JSON.placeId
        def imei = request.getHeader("imei")

        // get user object by imei
        def user = userService.getByImei(imei)

        if(!user) {
            throw new ApiException("Not registered", Constants.HttpCodes.BAD_REQUEST)
        }

        def place = userService.getPlaceById(placeId)

        Integer rating = ratings.toInteger()

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

 /*   def getStars(){

        //get data from request
        def placeId = request.JSON.placeId

        def place = userService.getPlaceById(placeId)

        def stars = ratingService.getPlaceStars(place)

        place.stars = stars

        place.save(flush: true, failOnError: true)

        def resp = [stars: stars]

        render resp as JSON

    }
*/




    // ----------------------- Private APIs ---------------------------//
}
