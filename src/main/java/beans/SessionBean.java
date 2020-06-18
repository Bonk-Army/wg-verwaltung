package beans;

import javax.enterprise.context.SessionScoped;

/**
 * Session bean declared as SessionScoped by default because we need it during the whole session.
 * Must implement java.io.Serializable.
 */
@SessionScoped
public class SessionBean implements java.io.Serializable{
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
