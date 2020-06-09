package view.parts.ContentSubparts;

import view.parts.ContentPart;

public class Register extends ContentPart{

    public Register() {
        super();
        generateContent();
    }


    private void generateContent() {
        this.content = readContentTemplate("HTMLTemplates", "Register", "html");
    }
}
