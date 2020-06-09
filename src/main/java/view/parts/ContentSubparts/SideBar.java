package view.parts.ContentSubparts;

import view.parts.ContentPart;

public class SideBar extends ContentPart {

    /**
     * No Special Attributes ... :/
     */
    public SideBar(){
        generateContent();
    }

    /**
     * Generating the Component
     */
    private void generateContent() {
        this.content = readScriptTemplate ("sidebar","js");
        this.content += readContentTemplate("sidebar","html");
    }
}
