package locateplusserver.services

import grails.gorm.transactions.Transactional
import locateplusserver.domains.User
import locateplusserver.domains.Category
import locateplusserver.domains.Rating
import locateplusserver.domains.Place
import locateplusserver.domains.Facility
import locateplusserver.ApiException
import locateplusserver.Constants

@Transactional
class UserService {
    // ----------------------- Dependencies ---------------------------//
    // ----------------------- Getter methods ---------------------------//

    // Method to get user object by IMEI number
    User getByImei(String imei) {

        User user = User.findByImei(imei)

        if(!user)
        {
            throw new ApiException("Not Registered", Constants.HttpCodes.BAD_REQUEST)
        }

        user
    }

    // Method to get user object by ID
    User getById(long id) {
        User user = User.findById(id)

        user
    }

    Facility getFacilityById(def id) {

        Facility facility = Facility.findById(id)

        facility
    }

    def isUpdateRequired(User user){

        def status = user.updateRequired

        return status
    }


    Category getCategoryById(String id) {

        def category = Category.findById(id)

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

    def updateAllUserStatus(){

        def userList = User.getAll()

        userList.each {
            it.updateRequired = true
            it.save()
        }

    }

    def getPlaceById(placeId) {

        def place = Place.findById(placeId)

        return place
    }

    /*def getStars(Place place) {

        def placeId = Place.Id

        def ratings = Rating.findAllById(placeId)

        ratings.each{ rate->
            def rates = Rating.PlaceId

            if(placeId == rates) {
                def r1 = null
                def count = 0
                r1.push()
            }
        }
    }*/

    // ----------------------- Converter methods ---------------------------//
    def toJson(User user) {
        return [
            app_id:   user.id,
            name: user.name,
            updateRequired: user.updateRequired
        ]
    }



    // ----------------------- Public methods ---------------------------//
    // ----------------------- Private methods ---------------------------//
}
