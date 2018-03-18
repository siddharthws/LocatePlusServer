package locateplusserver
import grails.converters.JSON
import locateplusserver.domains.Place
import locateplusserver.domains.Category

class Marshaller {


    static def serializePlace(Place place){
        def category = serializeCategory(place.category)
        return [
                id:            place.id,
                name:          place.name,
                latitude:      place.latitude,
                longitude:     place.longitude,
                category:      category,
                address:       place.address
        ]
    }

    static def serializeCategory(Category category){
        return [
                id:            category.id,
                name:          category.name,
        ]
    }




}
