package beans;


/**
 * Session bean declared as SessionScoped by default because we need it during the whole session.
 * Must implement java.io.Serializable.
 */
public class SessionBean {
    private String userId = "";

    public SessionBean(String userId) {
        this.userId = userId;
    }

    public SessionBean() {
    }

    public String getUserId() {
        return userId;
    }
}
