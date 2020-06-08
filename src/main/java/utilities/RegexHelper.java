package utilities;

public class RegexHelper {
    public static void main(String[] args) {
        System.out.println(checkEmail("patrick@mueller-patrick.tech"));
    }

    public static boolean checkUsername(String username){
        return username.matches(".*;") ? false : true;
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
