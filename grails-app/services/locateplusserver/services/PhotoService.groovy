package locateplusserver.services

import grails.gorm.transactions.Transactional
import locateplusserver.domains.Place
import locateplusserver.domains.Photo
import locateplusserver.ApiException
import locateplusserver.Role
import locateplusserver.Constants

@Transactional
class PhotoService {
    // ----------------------- Dependencies ---------------------------//
    def userService
    def updateService

    // ----------------------- Public methods ---------------------------//

    def putPhoto(String uuid){

        // Create new Photo object
        Photo photo = new Photo(uuid:uuid)

        //Save changes
        photo.save(flush: true, failOnError: true)

    }

    // Returns photo object based on uuid
    def getPhoto(def uuid){

        // Find photo by uuid
        def photo = Photo.findByUuid(uuid)

        // Check if photo present
        if(!photo){
            throw new ApiException("Photo not found", Constants.HttpCodes.BAD_REQUEST)
        }


        photo

    }

    // Removes photo
    def removePhoto(String uuid)
    {
        // Find photo by uuid
        Photo photo = findByUuid(uuid)

        // set is removed as true
        photo.isRemoved = true
        photo.save(flush: true,failOnError: true)

    }

    def getPhotoByPlace(def place)
    {

       def photosList = place.photos

        photosList

    }


    // ----------------------- Private methods ---------------------------//
    //Method to generate id and username


}
