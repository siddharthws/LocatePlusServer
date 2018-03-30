package locateplusserver.domains

class User {

    // Timestamp
    Date dateCreated
    Date lastUpdated

    // User Identifier
    String name
    String imei

    String udid

    boolean updateRequired = true

    static constraints = {
        udid nullable: true
    }

    static hasMany = [
            places : Place,
            ratings : Rating,
            reviews : Review
    ]

    static mapping = {
        table 'lp_user'            // table name 'user' is not allowed
    }
}
