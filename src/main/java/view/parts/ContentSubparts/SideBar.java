package view.parts.ContentSubparts;

import view.parts.ContentPart;

public class SideBar extends ContentPart {

    public SideBar(){
        this.content = readScriptTemplate ("sidebar","js");
        this.content += readContentTemplate("sidebar","html");
    }

}
