package utilities;

public class RegexHelper {
    public static void main(String[] args) {
        System.out.println(checkEmail("patrick@mueller-patrick.tech"));
    }

    /**
     * Checks if the entered String matches the rules we have for usernames.
     * Not allowed are whitespaces and ;?&% (; because we dont want SQLi)
     * @param username The username to be matched
     * @return If the username is allowed
     */
    public static boolean checkUsername(String username){
        return username.matches("^[^;?&%\\s]*$");
    }

    public static boolean checkEmail(String email){
        if(!email.matches(".*;")){
            if(email.matches(".+@.+\\..+")){
                return true;
            }
        }

        return false;
    }
}
