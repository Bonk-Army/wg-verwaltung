package view.parts.HTMLSubparts;

import view.parts.ContentPart;
import view.parts.HTMLPart;

public class Head extends HTMLPart {

    private String pageName ="";

    public Head(String pageName){
        super();
        this.pageName = pageName;
    }

    public Head(){
        super();
        this.pageName = "Pass den Seitennamen an du Esel!";
    }

    public void setPageName(String pageName){
        this.pageName = pageName;
    }

    @Override
    public String generateThisPart(){
        String className = this.getClass().getSimpleName();

        String result = "";

        result += readHTMLTemplate(className,className+"_Top","html");

        result += "<title>"+pageName+"</title>";

        for (ContentPart part : this.components){
            result += part.toString();
        }

        result += readHTMLTemplate(className,className+"_Bottom","html");

        return result;
    }

}
