package view.parts;

import java.util.ArrayList;

public abstract class HTMLPart extends Part {

    /**
     * Used to store the Content of any Part
     */
    protected ArrayList<ContentPart> components;

    public HTMLPart() {
        components = new ArrayList<ContentPart>();
    }

    /**
     * Adding a ContentPart to the components of the instance
     *
     * @param p Content part that gets added
     */
    public void addContentPart(ContentPart p) {
        this.components.add(p);
    }

    public void resetPopUp() {

    }

    /**
     * Reading out a template out of the "ressources/Templates/HTMLComponents" folder
     *
     * @param filename Filename
     * @param ending   Fileending
     * @return the content of the File
     */
    protected String readHTMLTemplate(String filename, String ending) {
        return readRessource("Templates", "HTMLComponents", filename, ending);
    }

    /**
     * Clearing the parts of the instance
     */
    public void clear() {
        this.components.clear();
    }

    /**
     * Generating the HTML of this part
     *
     * @return the HTML Source as a String
     */
    public String generateThisPart() {
        String className = this.getClass().getSimpleName();
        String result = "";
        //Preambel adden
        result += readHTMLTemplate(className + "_Top", "html");
        for (ContentPart part : this.components) {
            result += part.toString();
        }
        //Postfix adden
        result += readHTMLTemplate(className + "_Bottom", "html");
        return result;
    }
}
