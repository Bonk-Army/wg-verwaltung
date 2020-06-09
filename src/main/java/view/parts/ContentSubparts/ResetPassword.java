package view.parts.ContentSubparts;

import view.parts.ContentPart;

public class ResetPassword extends ContentPart {

    public ResetPassword(){
        generateContent();
    }

    private void generateContent() {
        this.content = readComponentTemplate("ResetPassword","html");
    }
}
