package locateplusserver.domains

class Rating {

    // Timestamp
    Date dateCreated
    Date lastUpdated

    int overAllRating = 0
    int photoRating = 0
    int facilitiesRating = 0
    int infoRating = 0

    static belongsTo = [
            place : Place,
            owner : User
    ]
}