package utilities;

public class RegexHelper {
    public static void main(String[] args) {
        System.out.println(checkEmail("patrick@mueller-patrick.tech"));
    }

    /**
     * Checks if the entered String matches the rules we have for usernames.
     * Not allowed are whitespaces and ;?&%' (; because we dont want SQLi)
     *
     * @param username The username to be matched
     * @return If the username is allowed
     */
    public static boolean checkUsername(String username) {
        return username.matches("^[^;?&%'\\s]*$");
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
            //Check for corret email address format
            if (email.matches(".+@.+\\..+")) {
                return true;
            }
        }

        return false;
    }
}
