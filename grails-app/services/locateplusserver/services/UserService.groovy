package locateplusserver.services

import grails.gorm.transactions.Transactional
import locateplusserver.domains.User
import locateplusserver.domains.Category
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


    Category getCategory(String name) {

        def category = Category.findByName(name)

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


    // ----------------------- Converter methods ---------------------------//
    def toJson(User user) {
        return [
            app_id:   user.id,
            name: user.name
        ]
    }



    // ----------------------- Public methods ---------------------------//
    // ----------------------- Private methods ---------------------------//
}
