package view.parts.ContentSubparts;

import view.parts.ContentPart;

public class Login_Register extends ContentPart {

    boolean status;
    String uid;

    public Login_Register(){
        this(false,"");
    }

    public Login_Register(boolean status, String uid){
        this.status = status;
        this.uid = uid;
        generateContent();
    }

    private void generateContent() {
        this.content = readContentTemplate("GUIElements","Login_Register","html");
        this.content += readScriptTemplate("Login_Register","js");
    }
}
