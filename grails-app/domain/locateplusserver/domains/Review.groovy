package locateplusserver.domains

class Review {

    // Timestamp
    Date dateCreated
    Date lastUpdated

    String review

    static belongsTo = [
            place : Place,
            owner : User
    ]
}