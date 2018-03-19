package locateplusserver

enum Role {
    ADMIN(1) , USER(2)

    //Role Related Constants
    public final int value

    //Constructor To Initialise Values Of Enum Elements
    Role(int value) {
        this.value = value
    }
}