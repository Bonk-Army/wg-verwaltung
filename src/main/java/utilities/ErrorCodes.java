package utilities;

public enum ErrorCodes {
    SUCCESS(""),        //Something was done successfully
    FAILURE(""),        //Something failed server-side
    WRONGENTRY(""),     //Something that has been entered was wrong
    WRONGUNAME(""),     //The entered username was wrong
    WRONGPASSWORD(""),  //The entered password was wrong
    TRYAGAIN("");       //The user should simply try it again

    private String message;

    ErrorCodes(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
