package locateplusserver.services

import grails.gorm.transactions.Transactional
import locateplusserver.domains.Update
import locateplusserver.domains.Place
import locateplusserver.ApiException
import locateplusserver.Role
import locateplusserver.Constants

@Transactional
class UpdateService {
    // ----------------------- Dependencies ---------------------------//
    def userService

    // ----------------------- Public methods ---------------------------//

    def updateFcStatus(){

        def update = Update.getAll()

        update[0].fcStatus += 1

        // save place
        update[0].save(flush: true, failOnError: true)

    }

    def updatePlaceStatus(){

        def update = Update.getAll()

        update[0].placeStatus += 1

        // save place
        update[0].save(flush: true, failOnError: true)
    }

    def intializeUpdates(){

        def update = Update.getAll()

        if(!update)
        {
             update = new Update(placeStatus:0, fcStatus:0)

            // save place
            update.save(flush: true, failOnError: true)
    }


    }

    def updateReviewStatus(def place){

        place.reviewStatus += 1

        // save place
        place.save(flush: true, failOnError: true)

    }

    def updatePhotoStatus(def place){

        place.placeStatus += 1

        // save place
        place.save(flush: true, failOnError: true)

    }

    // ----------------------- Private methods ---------------------------//


}
