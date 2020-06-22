package beans;

import models.User;
import utilities.ErrorCodes;
import utilities.SQLDCusers;

/**
 * Session bean that keeps relevant user data to authenticate the user and fetch data more quickly
 */
public class SessionBean {
    private String userId = "";
    private String username = "";
    private String firstName = "";
    private String lastName = "";
    private String wgId = "";
    private String wgName = "";
    private String email = "";
    private boolean loggedIn = false;

    public SessionBean(String userId) {
        this.userId = userId;

        User thisUser = SQLDCusers.getAllUserData(userId);

        this.username = thisUser.getUsername();
        this.firstName = thisUser.getFirstName();
        this.lastName = thisUser.getLastName();
        this.wgId = thisUser.getWgId();
        this.wgName = thisUser.getWgName();
        this.email = thisUser.getEmail();
        this.loggedIn = true;
    }

    public SessionBean() {
    }

    /**
     * Logs the user out
     *
     * @return if it was successful
     */
    public ErrorCodes logout() {
        ErrorCodes status = SQLDCusers.setCookiePostfix(this.username, "") ? ErrorCodes.SUCCESS : ErrorCodes.FAILURE;

        if (status == ErrorCodes.SUCCESS) {
            this.loggedIn = false;
            this.userId = "";
            this.username = "";
            this.firstName = "";
            this.lastName = "";
            this.wgId = "";
            this.wgName = "";
            this.email = "";
        }

        return status;
    }

    /*
      /$$$$$$              /$$     /$$                                               /$$        /$$$$$$              /$$     /$$
     /$$__  $$            | $$    | $$                                              /$$/       /$$__  $$            | $$    | $$
    | $$  \__/  /$$$$$$  /$$$$$$ /$$$$$$    /$$$$$$   /$$$$$$   /$$$$$$$           /$$/       | $$  \__/  /$$$$$$  /$$$$$$ /$$$$$$    /$$$$$$   /$$$$$$   /$$$$$$$
    | $$ /$$$$ /$$__  $$|_  $$_/|_  $$_/   /$$__  $$ /$$__  $$ /$$_____/          /$$/        |  $$$$$$  /$$__  $$|_  $$_/|_  $$_/   /$$__  $$ /$$__  $$ /$$_____/
    | $$|_  $$| $$$$$$$$  | $$    | $$    | $$$$$$$$| $$  \__/|  $$$$$$          /$$/          \____  $$| $$$$$$$$  | $$    | $$    | $$$$$$$$| $$  \__/|  $$$$$$
    | $$  \ $$| $$_____/  | $$ /$$| $$ /$$| $$_____/| $$       \____  $$        /$$/           /$$  \ $$| $$_____/  | $$ /$$| $$ /$$| $$_____/| $$       \____  $$
    |  $$$$$$/|  $$$$$$$  |  $$$$/|  $$$$/|  $$$$$$$| $$       /$$$$$$$/       /$$/           |  $$$$$$/|  $$$$$$$  |  $$$$/|  $$$$/|  $$$$$$$| $$       /$$$$$$$/
     \______/  \_______/   \___/   \___/   \_______/|__/      |_______/       |__/             \______/  \_______/   \___/   \___/   \_______/|__/      |_______/
    */

    // Getters and Setters for use with JSPs

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getWgId() {
        return wgId;
    }

    public String getWgName() {
        return wgName;
    }

    public String getEmail() {
        return email;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setWgId(String wgId) {
        this.wgId = wgId;
    }

    public void setWgName(String wgName) {
        this.wgName = wgName;
    }
}
