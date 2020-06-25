package models;

/**
 * Model for users used in the backend exclusively
 */
public class User {
    private String userId;
    private String username;
    private String firstName;
    private String lastName;
    private String nameString;
    private String wgId;
    private String wgName;
    private String email;

    public User(String userId, String username, String firstName, String lastName, String nameString, String wgId, String wgName, String email) {
        this.userId = userId;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nameString = nameString;
        this.wgId = wgId;
        this.wgName = wgName;
        this.email = email;
    }

    public User() {
    }

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

    public String getNameString() {
        return nameString;
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

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setNameString(String nameString) {
        this.nameString = nameString;
    }

    public void setWgId(String wgId) {
        this.wgId = wgId;
    }

    public void setWgName(String wgName) {
        this.wgName = wgName;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
