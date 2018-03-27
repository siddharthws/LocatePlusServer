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

    def getRatingByUserAndPlace(User user , Place place){

        def rating = Rating.findByOwnerAndPlace(user,place)

        rating

    }

    def getRatingByPlace(Place place){

        
        def ratingList = Rating.findAllByPlace(place)

        ratingList
    }

    def getOverallPlaceRating(Place place)
    {

        def ratingList = getRatingByPlace(place)

        def noOfUsers = ratingList.size
        def ratings
        def rating = 0
        ratingList.each{member->

            if(!member){

                rating += member.overAllRating
            }
        }

        if(!rating==0 && !noOfUsers==0)
        {
             ratings = rating/noOfUsers
        }

        ratings = 4

        ratings

    }

    def getTotalUsersForPlace(Place place){

        def ratingList = getRatingByPlace(place)

        def noOfUsers = ratingList.size

        noOfUsers

    }



    // ----------------------- Private methods ---------------------------//
}
