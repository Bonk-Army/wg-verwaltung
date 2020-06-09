package view.parts.ContentSubparts;

import view.parts.ContentPart;

public class Register extends ContentPart{
    boolean status;
    String uid;

    public Register() {
        this(false, "");
    }

    public Register(boolean status, String uid) {
        this.status = status;
        this.uid = uid;
        generateContent();
    }

    private void generateContent() {
        this.content = readContentTemplate("GUIElements", "Register", "html");
        this.content += readScriptTemplate("Login_Register", "js");
    }
}
