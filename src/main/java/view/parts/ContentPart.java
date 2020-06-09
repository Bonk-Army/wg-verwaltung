package view.parts;

abstract public class ContentPart extends Part{

    protected String content = "";

    public ContentPart(){
        super();
    }

    protected String readContentTemplate (String filename,String ending){
       return readRessource("Contents","",filename,ending);
    }

    protected String readComponentTemplate (String filename,String ending){
        return readRessource("Templates","Components",filename,ending);
    }

    protected String readImportTemplate (String filename,String ending){
        return readRessource("Templates","Imports",filename,ending);
    }

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
