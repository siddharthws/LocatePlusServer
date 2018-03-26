package locateplusserver.services

import grails.gorm.transactions.Transactional
import locateplusserver.domains.User
import locateplusserver.domains.Category
import locateplusserver.domains.Rating
import locateplusserver.domains.Review
import locateplusserver.domains.Place
import locateplusserver.domains.Facility
import locateplusserver.domains.Update
import locateplusserver.ApiException
import locateplusserver.Constants

@Transactional
class UserService {
    // ----------------------- Dependencies ---------------------------//

    // ----------------------- Getter methods ---------------------------//

    // Method to get user object by IMEI number
    User getByImei(String imei) {

        User user = User.findByImei(imei)

        user
    }

    // Method to get user object by ID
    User getById(long id) {
        User user = User.findById(id)

        user
    }

    Facility getFacilityById(def id) {

        def facility = Facility.findById(id)

        if(!facility)
        {
            throw new ApiException("Invalid Facility", Constants.HttpCodes.BAD_REQUEST)
        }

        facility
    }


    // method to get category object by ID
    Category getCategoryById(def id) {

        def category = Category.findById(id)

        if(!category)
        {
            throw new ApiException("Invalid Category", Constants.HttpCodes.BAD_REQUEST)
        }

        category
    }

    // Method to get all places
    def getAllPlaces()
    {
        // Get all places List
        def placeList = Place.findAllByIsRemoved(false)

        placeList
    }

    def getAllCategories(){

        def categoriesList = Category.getAll()

        categoriesList
    }

    def getAllFacilities(){

        def facilitiesList = Facility.getAll()

        facilitiesList
    }


     def getPlaceById(placeId) {

        def place = Place.findById(placeId)

        if(!place) {
            throw new ApiException("Place does not Exist", Constants.HttpCodes.BAD_REQUEST)
        }

        return place
    }


    def getStars(Place place) {

        def placeId = place.Id

        def ratings = Rating.findAllById(placeId)

        def list = []

        ratings.each{ rate->

                list.add(rate.rating)

        }

        def stars = list.sum() / list.size()

        stars
    }

    def getReviewsById(def id){

        def place = getPlaceById(id)

        def reviewsList = Review.findAllByPlace(place)

        reviewsList

    }


    // ----------------------- Converter methods ---------------------------//
    def toJsonUser(User user) {
        def update = Update.getAll()

        return [
            app_id:   user.id,
            name: user.name,
            serverResponseFC: update[0].fcStatus,
            serverResponseGP: update[0].placeStatus

        ]
    }

    // ----------------------- Public methods ---------------------------//
    // ----------------------- Private methods ---------------------------//
}
