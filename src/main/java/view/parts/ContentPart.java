package view.parts;

import view.config.globalConfig;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

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
