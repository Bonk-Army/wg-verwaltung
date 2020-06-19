package utilities;

/**
 * Utility class used to check all user entries for illegal characters
 */
public class RegexHelper {
    /**
     * Checks if the entered String matches the rules we have for usernames.
     * Not allowed are whitespaces and ;?&%/\' (; because we dont want SQLi)
     *
     * @param toCheck The String to be matched
     * @return If the string is allowed
     */
    public static boolean checkString(String toCheck) {
        return toCheck.matches("^[^\\\\;?&%/'\\s]*$");
    }

    /**
     * Checks if the entered String matches the rules we have for usernames.
     * Not allowed are ;\' (; because we dont want SQLi)
     *
     * @param toCheck The Text to be matched
     * @return If the text is allowed
     */
    public static boolean checkText(String toCheck) {
        return toCheck.matches("^[^\\\\;']*$");
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
        if (email.matches("^[^\\\\;?&%/'\\s]*$")) {
            //Check for correct email address format
            if (email.matches(".+@.+\\..+")) {
                return true;
            }
        }

        return false;
    }
}
