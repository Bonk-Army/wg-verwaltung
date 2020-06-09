package view.parts.ContentSubparts;

import view.parts.ContentPart;

public class Login extends ContentPart {

    boolean status;
    String uid;

    public Login(){
        this(false,"");
    }

    public Login(boolean status, String uid){
        this.status = status;
        this.uid = uid;
        generateContent();
    }

    private void generateContent() {
        this.content = readContentTemplate("HTMLTemplates","Login","html");
    }
}
