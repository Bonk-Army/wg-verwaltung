package view.parts.ContentSubparts;

import view.parts.ContentPart;

public class Register extends ContentPart{

    public Register() {
        super();
        generateContent();
    }


    private void generateContent() {
        this.content = readComponentTemplate("Register", "html");
    }
}
