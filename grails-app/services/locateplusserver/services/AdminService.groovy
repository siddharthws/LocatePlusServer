package locateplusserver.services

import grails.gorm.transactions.Transactional
import locateplusserver.domains.Admin
import locateplusserver.ApiException

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



    // ----------------------- Private methods ---------------------------//

    def toJsonAdmin(Admin admin) {

        return [
                admin_id:   admin.id,
                username: admin.userName,
                password: admin.password

        ]
    }


}
