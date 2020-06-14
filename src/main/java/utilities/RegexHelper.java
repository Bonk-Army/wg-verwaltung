package utilities;

public class RegexHelper {
    /**
     * Checks if the entered String matches the rules we have for usernames.
     * Not allowed are whitespaces and ;?&%' (; because we dont want SQLi)
     *
     * @param toCheck The username to be matched
     * @return If the username is allowed
     */
    public static boolean checkString(String toCheck) {
        return toCheck.matches("^[^;?&%'\\s]*$");
    }

    /**
     * Checks if the entered String matches the rules we have for email addresses.
     * Not allowed are whitespaces and ;?&%'
     *
     * @param email The email address String to be matched
     * @return If the email address is allowed
     */
    public static boolean checkEmail(String email) {
        //Check for illegal characters
        if (email.matches("^[^;?&%'\\s]*$")) {
            //Check for correct email address format
            if (email.matches(".+@.+\\..+")) {
                return true;
            }
        }

        return false;
    }
}
