package locateplusserver.services

import grails.gorm.transactions.Transactional
import locateplusserver.domains.User

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

    // ----------------------- Converter methods ---------------------------//
    def toJson(User user) {
        return [
            id:   user.id,
            name: user.name
        ]
    }

    // ----------------------- Public methods ---------------------------//
    // ----------------------- Private methods ---------------------------//
}
