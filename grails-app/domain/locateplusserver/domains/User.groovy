package locateplusserver.domains

class User {

    // Timestamp
    Date dateCreated
    Date lastUpdated

    // User Identifier
    String name
    String imei

    boolean updateRequired = true

    locateplusserver.Role role

    static constraints = {
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
