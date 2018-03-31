package locateplusserver.domains

class Complaints {

    // Timestamp
    Date dateCreated
    Date lastUpdated

    String description

    String title

    int resolvedCount = 0

    static hasMany = [
            photos : Photo
    ]

}