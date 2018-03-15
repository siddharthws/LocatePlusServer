package locateplusserver.domains

class User {

    // Timestamp
    Date dateCreated
    Date lastUpdated

    // User Identifier
    String name
    String imei

    static constraints = {
    }

    static mapping = {
        table 'lp_user'            // table name 'user' is not allowed
    }
}
