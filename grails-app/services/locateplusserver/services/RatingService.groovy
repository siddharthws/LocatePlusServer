package locateplusserver.services

import grails.gorm.transactions.Transactional
import locateplusserver.domains.User
import locateplusserver.domains.Rating
import locateplusserver.domains.Place

@Transactional
class RatingService {
    // ----------------------- Dependencies ---------------------------//

    def userService

    // ----------------------- Converter methods ---------------------------//




    // ----------------------- Public methods ---------------------------//

    def createNewRating(String ratingObject ,Place place , User user ,Integer rating){

        Rating newrating = new Rating(
                (ratingObject): rating,
                owner: user
        )

        // associate review to a place
        place.addToRatings(newrating)

        //save place object
        place.save(flush: true, failOnError: true)

        true
    }


    def getRatingByUserAndPlace(User user, Place place) {

        def rating = Rating.findByOwnerAndPlace(user, place)

        rating

    }

    def getRatingByPlace(Place place) {


        def ratingList = Rating.findAllByPlace(place)

        ratingList
    }




    // ----------------------- Rating  methods ---------------------------//

    def photoRatingAlgorithm(def photoList) {

        def photoCount = photoList.size()

        def weightedRating = 100 / photoCount

        def photoTotalRating = 0

        photoList.each { member ->

            if(member){
                def uuid = member.uuid
                def rating = member.rating
                def ratingint = rating.toInteger()

                switch (ratingint){

                    case 1:
                        photoTotalRating += weightedRating
                        break
                    case -1:
                        photoTotalRating -= weightedRating
                        break
                    case 0:
                        // DO nothing
                    break
                }
            }

        }

        if(photoTotalRating < weightedRating){

            photoTotalRating = 0
        }

        photoTotalRating


    }

    def infoRatingAlgorithm(def infoRating){

        def weightedRating = 100 / 3
        def infoTotalRating = 0

        infoRating.each { member ->

            def uuid = member.uuid
            def rating = member.rating
            def ratingint = rating.toInteger()

            switch (ratingint) {

                case 1:
                    infoTotalRating += weightedRating
                    break
                case -1:
                    infoTotalRating -= weightedRating
                    break
                case 0:
                    // Do nothing
                    break
            }
        }

        if(infoTotalRating < weightedRating){

            infoTotalRating = 0
        }


        def infoTotalRatingRounded = Math.round(infoTotalRating * 100) / 100

        infoTotalRatingRounded

     }


    def facilitiesRatingAlgorithm(def facilityList){

        def facilityCount = facilityList.size()

        def weightedRating = 100 / facilityCount

        def facilityTotalRating = 0

        facilityList.each { member ->

            if(member){
                def id = member.id
                def rating = member.rating
                def ratingint = rating.toInteger()
                switch (ratingint){

                    case 1:
                        facilityTotalRating += weightedRating
                        break
                    case -1:
                        facilityTotalRating -= weightedRating
                        break
                    case 0:
                        // DO nothing
                        break
                }
            }

        }
        log.error("hello:"+weightedRating)

        if(facilityTotalRating < weightedRating){

            facilityTotalRating = 0
        }
        log.error("total:"+facilityTotalRating)

        facilityTotalRating


    }



    def getPlaceStars(Place place)
    {

        def ratingList = getRatingByPlace(place)

        def ratingListSize = ratingList.size()

        def totalRating = 0

        def stars = 0

        ratingList.each{member->

            def infoRating = member.infoRating
            def photoRating = member.photoRating
            def facilitiesRating = member.facilitiesRating
            def overAllRating = member.overAllRating

            infoRating = infoRating/20
            photoRating = photoRating/20
            facilitiesRating = facilitiesRating/20

            totalRating += infoRating + photoRating + facilitiesRating + overAllRating

        }

        if(ratingListSize>0){
            stars = totalRating/ratingListSize
        }

        stars

    }

    def getTotalUsersForPlace(Place place){

        def ratingList = getRatingByPlace(place)

        def noOfUsers = ratingList.size

        noOfUsers

    }




    // ----------------------- Public methods ---------------------------//




    // ----------------------- Private methods ---------------------------//
}
