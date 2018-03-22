package locateplusserver.domains

class Place {

    // Timestamp
    Date dateCreated
    Date lastUpdated

    // Location
    double latitude
    double longitude

    // User Identifier
    String name
    String address
    String description

    Category category

    boolean isRemoved = false

    double stars

    static belongsTo = [
           owner : User
    ]

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
