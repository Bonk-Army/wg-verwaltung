package main.java.beans;

// Imports
// import models.User;
import main.java.utilities.PasswordHasher;

public class LoginBean {
    // Variables

    // private User user;

    // Public Methods
    public boolean login(String username, String password){
        String salt = ""; //Get salt from SQL
        String hash = ""; //Get hash from SQL

        String newHash = PasswordHasher.hashPassword(password, salt.getBytes());

        return newHash.equals(hash);
    }
        // checkLogin() { SQL Query: Try to find combination of username and password }
        // Check if username and password is in SQL
            // true -> user = user
            // else -> return "Wrong combination of username and password"

        // createUser() { new User = User(username, password) -> merge to sql}

        // sendPassword() { mail service }

    // Private Methods

    // Getter and Setter

        // getUser() { return this.User; }
}
