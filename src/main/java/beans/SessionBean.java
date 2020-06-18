package beans;


/**
 * Session bean declared as SessionScoped by default because we need it during the whole session.
 * Must implement java.io.Serializable.
 */
public class SessionBean {
    private String userId = "";
    private String username = "";
    private String firstName = "";
    private String lastName = "";
    private String wgId = "";
    private String wgName = "";

    public SessionBean(String userId) {
        LoginBean loginBean = new LoginBean();

        this.userId = userId;
        this.username = loginBean.getUsernameById(userId);
        this.firstName = loginBean.getFirstName(userId);
        this.lastName = loginBean.getLastName(userId);
        this.wgId = loginBean.getWgIdByUserId(userId);
        this.wgName = loginBean.getWgNameByUserId(userId);
    }

    public SessionBean() {
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

    public String getWgId() {
        return wgId;
    }

    public String getWgName() {
        return wgName;
    }
}
