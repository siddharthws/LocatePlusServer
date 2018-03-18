package locateplusserver
import grails.converters.JSON
import locateplusserver.domains.Place

class Marshaller {


    static def serializePlace(locateplusserver.domains.Place place){
        return [
                id:            place.id,
                name:          place.name,
                latitude:      place.latitude,
                longitude:     place.longitude,
                category:      place.category,
                address:       place.address
        ]
    }




}
