package view.parts.ContentSubparts;


import view.parts.ContentPart;

public class TemplateFromString extends ContentPart {
    public TemplateFromString(String content) {
        this.content = content;
    }

    /**
     * Generating the Component
     */
    private void generateContent() {
        this.content = readComponentTemplate("Login","html");
    }
}
