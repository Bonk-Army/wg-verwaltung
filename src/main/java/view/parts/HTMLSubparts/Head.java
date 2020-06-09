package view.parts.HTMLSubparts;

import config.globalConfig;
import view.parts.ContentPart;
import view.parts.HTMLPart;

import java.util.ArrayList;

public class Head extends HTMLPart {

    /**
     * Saving the Page Name and an Arraylist of the css files
     */
    private String pageName = "";
    private ArrayList<String> cssFiles;

    public Head(String pageName) {
        super();
        this.pageName = pageName;
        cssFiles = new ArrayList<String>();
    }

    /**
     * if theres no name given, use a name to get attention....
     */
    public Head() {
        this("Find nen Namen du Esel!");
    }

    /**
     * Adding an css to the head object
     *
     * @param filename
     * @param ending
     */
    public void addCSS(String filename, String ending) {
        // TODO Fixx the CSS ISSUES
        this.cssFiles.add(generatePath("classes", "Styles", "", filename, ending));
        //this.cssFiles.add("./Styles/"+filename+"."+ending);
    }

    /**
     * Setting the PageName
     */
    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    /**
     * Clearing the component, SPECIALTY, because Head has a secound CSS List
     */
    @Override
    public void clear() {
        super.clear();
        this.cssFiles.clear();
    }

    /**
     * Overriding the generateThisPart to add the special of the Head, the CSS and Imports
     *
     * @return
     */
    @Override
    public String generateThisPart() {
        String className = this.getClass().getSimpleName();

        String result = "";

        result += readHTMLTemplate(className + "_Top", "html");

        result += "<title>" + pageName + "</title>";

        for (String cssPath : cssFiles) {
            result += "<link rel=\"stylesheet\" type=\"text/css\" href=\"" + cssPath + "\">";
        }

        for (ContentPart part : this.components) {
            result += part.toString();
        }

        result += readHTMLTemplate(className + "_Bottom", "html");

        this.clear();

        return result;
    }

}
