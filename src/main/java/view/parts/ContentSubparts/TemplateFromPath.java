package view.parts.ContentSubparts;

import view.parts.ContentPart;

public class TemplateFromPath extends ContentPart {

    /**
     * No Special Attributes ... :/
     * Just using different Methods to declare which Sort of file to import
     */
    public TemplateFromPath(String type,String filename,String ending){
         generateContent(type,filename,ending);
    }

    private void generateContent(String type,String filename,String ending) {
        switch(type){
            case "Component" : this.content = readComponentTemplate(filename,ending); break;
            case "Import" : this.content = readImportTemplate(filename,ending); break;
            case "Content" : this.content = readContentTemplate(filename,ending); break;
        }
    }
}
