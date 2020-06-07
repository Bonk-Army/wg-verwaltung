package view.parts.ContentSubparts;

import view.parts.ContentPart;

public class TemplateFromPath extends ContentPart {
    public TemplateFromPath(String subtype, String filename,String ending){
         this.content = readContentTemplate(subtype,filename,ending);
    }
}
