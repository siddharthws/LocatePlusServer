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

    // Place details update fields
    double reviewStatus = 0
    double photoStatus = 0

    double stars = 0

    static belongsTo = [
           owner : User
    ]

    static hasMany = [
            ratings:    Rating,
            reviews:    Review,
            facilities: Facility
    ]

    static constraints = {
            description nullable: true
    }

    static mapping = {
    }
}
