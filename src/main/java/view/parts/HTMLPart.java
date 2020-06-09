package view.parts;

import java.util.ArrayList;

import view.parts.ContentSubparts.TemplateFromString;

public abstract class HTMLPart extends Part{

    protected ArrayList<ContentPart> components;

    public HTMLPart(){
        components = new ArrayList<ContentPart>();
    }

    //adding Content Parts
    public void addContentPart(ContentPart p){
        this.components.add(p);
    }

    //Reading in Template in HTML Folder
    protected String readTemplate (String subtype,String filename,String ending){
        return readRessource("Templates",subtype,filename,ending);
    }

    public void clear(){
        this.components.clear();
    }

    public String generateThisPart(){
        String className = this.getClass().getSimpleName();

        String result = "";

        result += readTemplate("HTMLComponents",className+"_Top","html");

        for (ContentPart part : this.components){
            result += part.toString();
        }

        result += readTemplate("HTMLComponents",className+"_Bottom","html");

        return result;
    }
}
