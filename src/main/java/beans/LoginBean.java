package beans;

// Imports
// import models.User;
import main.java.utilities.PasswordHasher;

/**
 * Bean that handles all backend logic and database callouts required for user login and registration.
 */
public class LoginBean {
    // Variables

    // private User user;

    // Public Methods
    /**
     * Called by LoginServlet upon login action from user. Returns user id in case of successfull login, otherwise
     * returns an empty String
     * @param username The entered username
     * @param password The entered password
     * @return The user id or an empty String.
     */
    public String login(String username, String password){
        String salt = ""; //Get salt from SQL
        String hash = ""; //Get hash from SQL

        String newHash = PasswordHasher.hashPassword(password, salt.getBytes());

        return newHash.equals(hash) ? getUserId(username) : "";
    }

    /**
     * Called by LoginServlet upon register action from user. Returns the new user id in case the registration is
     * successful, otherwise returns an empty String
     * @param username The entered username (must not exist yet)
     * @param password The entered password
     * @param email The entered email (must not exist yet)
     * @return The user id or an empty String.
     */
    public String register(String username, String password, String email){
        byte[] salt = PasswordHasher.generateSalt();

        String hash = PasswordHasher.hashPassword(password, salt);

        //Call SQL to ask if username / email already exists. If not, continue
        if(true){
            //If either exists, return an empty String
            return "";
        }else{
            //Create new user. Generate new random user id (must be unique, so check that!) and save id, username,
            //password hash and email to sql
            return "";
        }

    }

    // sendPassword() { mail service }

    // Private Methods

    /**
     * Fetches the saved user id for the given username from sql
     * @param username The username of the required id
     * @return The ID as a String
     */
    private String getUserId(String username){
        return "";
    }

    // Getter and Setter

    // getUser() { return this.User; }
}
