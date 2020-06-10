package view.parts.ContentSubparts;

import view.parts.ContentPart;

public class Login extends ContentPart {

    /**
     * Variables to save specific attributes
     */
    private boolean status;
    private String uid;

    /**
     * Default constructor
     */
    public Login() {
        this(false, "");
    }

    /**
     * Constructor to instanciate the Login Component
     *
     * @param status
     * @param uid
     */
    public Login(boolean status, String uid) {
        this.status = status;
        this.uid = uid;
        generateContent();
    }

    /**
     * Generating the Component
     */
    private void generateContent() {
        this.content = readComponentTemplate("Login", "html");
    }
}
