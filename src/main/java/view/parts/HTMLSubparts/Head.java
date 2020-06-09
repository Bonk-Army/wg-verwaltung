package view.parts.HTMLSubparts;

import view.parts.ContentPart;
import view.parts.HTMLPart;

import java.util.ArrayList;

public class Head extends HTMLPart {

    private String pageName ="";
    private ArrayList<String> cssFiles;

    public Head(String pageName){
        super();
        this.pageName = pageName;
        cssFiles = new ArrayList<String>();
    }

    public Head(){
        this("Find nen Namen du Esel!");
    }

    public void setCssFilePath(String cssFilePath) {
        this.cssFiles.add(cssFilePath);
    }

    public void setPageName(String pageName){
        this.pageName = pageName;
    }

    @Override
    public void clear() {
        super.clear();
        this.cssFiles.clear();
    }

    @Override
    public String generateThisPart(){
        String className = this.getClass().getSimpleName();

        String result = "";

        result += readHTMLTemplate(className,className+"_Top","html");

        result += "<title>"+pageName+"</title>";

        //Beispielpath : ./assets/styles/main.css
        for(String cssPath : cssFiles){
            result += "<link rel=\"stylesheet\" type=\"text/css\" href=\""+cssPath+"\">";
        }

        for (ContentPart part : this.components){
            result += part.toString();
        }

        result += readHTMLTemplate(className,className+"_Bottom","html");

        this.clear();

        return result;
    }

}
