package view.parts;

abstract public class ContentPart extends Part{

    protected String content = "";

    public ContentPart(){
        super();
    }

    protected String readContentTemplate (String subtype,String filename,String ending){
       return readRessource("Content",subtype,filename,ending);
    }

    @Override
    public String toString() {
        return this.content;
    }
}
