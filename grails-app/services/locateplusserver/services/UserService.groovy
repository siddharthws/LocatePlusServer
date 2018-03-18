package locateplusserver.services

import grails.gorm.transactions.Transactional
import locateplusserver.domains.User
import locateplusserver.domains.Category
import locateplusserver.domains.Place
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

    Category getCategory(String name) {

        def category = Category.findByName(name)

        category
    }

    // Method to get all places
    def getAllPlaces()
    {
        // Get all places List
        def placeList = Place.getAll()

        placeList
    }

    // ----------------------- Converter methods ---------------------------//
    def toJson(User user) {
        return [
            user_id:   user.id,
            name: user.name
        ]
    }



    // ----------------------- Public methods ---------------------------//
    // ----------------------- Private methods ---------------------------//
}
