package locateplusserver.domains

class Rating {

    // Timestamp
    Date dateCreated
    Date lastUpdated

    int overAllRating

    int photoRating
    int faciltiesRating
    int infoRating

    static belongsTo = [
            place : Place,
            owner : User
    ]
}