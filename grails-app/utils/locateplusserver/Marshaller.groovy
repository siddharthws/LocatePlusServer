package locateplusserver

import locateplusserver.domains.Place
import locateplusserver.domains.Category
import locateplusserver.domains.Facility
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

        return [
                placeId:            place.id,
                name:          place.name,
                latitude:      place.latitude,
                longitude:     place.longitude,
                category:      category,
                address:       place.address,
                facilities :   facilities
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



}
