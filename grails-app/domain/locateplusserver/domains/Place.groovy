package locateplusserver.domains

class PlaceDetails {

    // Timestamp
    Date dateCreated
    Date lastUpdated

    // Location
    double latitude
    double longitude

    // User Identifier
    String name
    String address

    Category category

    double stars

    static hasMany = [
            ratings:    Rating,
            reviews:    Review,
            facilities: Facility
    ]

    static constraints = {
    }

    static mapping = {
    }
}
