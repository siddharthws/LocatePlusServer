package locateplusserver.domains

class Rating {

    // Timestamp
    Date dateCreated
    Date lastUpdated

    int rating

    static belongsTo = [
            place : Place,
            owner : User
    ]
}