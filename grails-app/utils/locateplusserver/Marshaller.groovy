package locateplusserver

import locateplusserver.domains.Place
import locateplusserver.domains.Category
import locateplusserver.domains.Facility
import locateplusserver.domains.Photo
import org.grails.web.json.JSONArray

class Marshaller {



    def userService

    static def serializePlace(Place place){

        def category = serializeCategory(place.category)

        def facilitiesList = place.facilities

        def facilities = new JSONArray()

        facilitiesList.each { member ->

            facilities.add(serializeFacility(member))
        }

//        def photoList = place.photos
//
//        def photos = new JSONArray()
//
//        photoList.each { member ->
//
//            photos.add(serializePhoto(member))
//        }

        return [
                placeId:       place.id,
                name:          place.name,
                latitude:      place.latitude,
                longitude:     place.longitude,
                category:      category,
                address:       place.address,
                facilities :   facilities,
                description :  place.description,
                stars :        place.stars
        ]
    }

    static def serializeCategory(Category category){
        return [
                id:            category.id,
                name:          category.name,
        ]
    }

    static def serializeFacility(Facility facility){
        return [
                id:            facility.id,
                name:          facility.name,
        ]
    }

    static def serializePhoto(Photo photo){
        return [
                id:            photo.id,
                name:          photo.uuid,
        ]
    }


}
