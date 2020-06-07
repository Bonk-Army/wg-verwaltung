package view.parts;

import java.util.ArrayList;

import view.parts.ContentSubparts.TemplateFromString;

public abstract class HTMLPart extends Part{

    ArrayList<ContentPart> components;

    public HTMLPart(){
        components = new ArrayList<ContentPart>();
    }

    //adding COntent Parts
    public void addContentPart(ContentPart p){
        this.components.add(p);
    }

    //Reading in Template in HTML Folder
    protected String readHTMLTemplate (String subtype,String filename,String ending){
        return readRessource("Framework//HTML",subtype,filename,ending);
    }

    public void clear(){
        this.components.clear();
    }

    public String generateThisPart(){
        String className = this.getClass().getSimpleName();

        String result = "";

        result += readHTMLTemplate(className,className+"_Top","html");

        for (ContentPart part : this.components){
            result += part.toString();
        }

        result += readHTMLTemplate(className,className+"_Bottom","html");

        return result;
    }
}
