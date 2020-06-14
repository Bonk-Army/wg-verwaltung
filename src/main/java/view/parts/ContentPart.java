package view.parts;

abstract public class ContentPart extends Part {

    /**
     * The content as a source HTML String
     */
    protected String content = "";

    public ContentPart() {
        super();
    }

    /**
     * Read a template from the folder"ressources/Content/"
     *
     * @param filename
     * @param ending
     * @return the content of the source as a String
     */
    protected String readContentTemplate(String filename, String ending) {
        return readRessource("src/main/webapp/assets/Contents", "", filename, ending);
    }

    /**
     * Read a template from the folder"ressources/Templates/Imports"
     *
     * @param filename
     * @param ending
     * @return the content of the source as a String
     */
    protected String readImportTemplate(String filename, String ending) {
        return readRessource("src/main/webapp/assets/Templates", "Imports", filename, ending);
    }

    /**
     * Read a template from the folder"ressources/Templates/Components"
     *
     * @param filename
     * @param ending
     * @return the content of the source as a String
     */
    protected String readComponentTemplate(String filename, String ending) {
        return readRessource("src/main/webapp/assets/Templates", "Components", filename, ending);
    }

    /**
     * Read a template from the folder"ressources/Scripts/"
     *
     * @param filename
     * @param ending
     * @return the content of the source as a String
     */
    protected String readScriptTemplate(String filename, String ending) {
        String result = "";
        result += "<script>";
        result += readRessource("src/main/webapp/assets/Scripts", "", filename, ending);
        result += "</script>";
        return result;
    }

    @Override
    public String toString() {
        return this.content;
    }
}
