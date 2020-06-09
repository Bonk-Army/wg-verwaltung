package view.parts.ContentSubparts;

import view.parts.ContentPart;

public class Register extends ContentPart{

    /**
     * No Special Attributes ... :/
     */
    public Register() {
        super();
        generateContent();
    }

    /**
     * Generating the Component
     */
    private void generateContent() {
        this.content = readComponentTemplate("Register", "html");
    }
}
