package locateplusserver.services

import grails.gorm.transactions.Transactional
import locateplusserver.domains.Admin
import locateplusserver.domains.Facility
import locateplusserver.ApiException
import locateplusserver.Constants
import locateplusserver.domains.Udid

@Transactional
class AdminService {
    // ----------------------- Dependencies ---------------------------//
    def userService

    // ----------------------- Public methods ---------------------------//

    // Method to get admin object by ID
    Admin getByName(def name) {

        Admin admin = Admin.findByUserName(name)

        admin
    }

    def checkUsername(def username)
    {
        if(!username)
        {
            throw new ApiException("Email not present", Constants.HttpCodes.BAD_REQUEST)
        }

    }

    def getPlaceByCategory(Category category){

        def placeList = Place.findAllByCategory(category)

        placeList

    }

    def getPlaceByFacility(Facility facility)
    {

        def placeList = Place.findAllByFacility(facility)

        placeList

    }

    def getByUdid(String udid , String name) {

        def udidList = Udid.findByUdidAndName(udid,name)

        udidList
    }

    // ----------------------- Private methods ---------------------------//

    def toJsonAdmin(Admin admin) {

        return [
                admin_id:   admin.id,
                username: admin.userName,
                password: admin.password

        ]
    }


}
