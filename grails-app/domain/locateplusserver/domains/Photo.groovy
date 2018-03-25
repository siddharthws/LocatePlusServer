package locateplusserver.domains

class Photo {

    // Timestamp
    Date dateCreated
    Date lastUpdated

    // Photo Identifier
    String uuid

    boolean isRemoved = false
    double inAppropriateCount = 0

}
