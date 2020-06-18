package beans;

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
