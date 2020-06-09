package view.parts;

abstract public class ContentPart extends Part{

    /**
     * The content as an source HTML String
     */
    protected String content = "";

    public ContentPart(){
        super();
    }

    /**
     * Read an template from the folder"ressources/Content/"
     * @param filename
     * @param ending
     * @return the content of the source as an String
     */
    protected String readContentTemplate (String filename,String ending){
       return readRessource("Contents","",filename,ending);
    }

    /**
     * Read an template from the folder"ressources/Templates/Imports"
     * @param filename
     * @param ending
     * @return the content of the source as an String
     */
    protected String readImportTemplate (String filename,String ending){
        return readRessource("Templates","Imports",filename,ending);
    }

    /**
     * Read an template from the folder"ressources/Templates/Components"
     * @param filename
     * @param ending
     * @return the content of the source as an String
     */
    protected String readComponentTemplate (String filename,String ending){
        return readRessource("Templates","Components",filename,ending);
    }

    /**
     * Read an template from the folder"ressources/Scripts/"
     * @param filename
     * @param ending
     * @return the content of the source as an String
     */
    protected String readScriptTemplate (String filename,String ending){
        String result = "";
        result += "<script>";
        result += readRessource("Scripts","",filename,ending);
        result += "</script>";
        return result;
    }

    @Override
    public String toString() {
        return this.content;
    }
}
