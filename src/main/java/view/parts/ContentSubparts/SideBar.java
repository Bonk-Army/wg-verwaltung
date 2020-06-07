package view.parts.ContentSubparts;

import view.parts.ContentPart;

public class SideBar extends ContentPart {

    public SideBar(){
        this.content = readContentTemplate("GUIElements","sidebar","html");
    }

}
