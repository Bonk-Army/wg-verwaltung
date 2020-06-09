package view.parts;

import config.globalConfig;

import java.io.*;

public abstract class Part {

    public Part(){
    }

    //Reading in Template in HTML Folder
    public String readRessource(String type,String subtype,String filename,String ending){
        try {
            BufferedReader in;
            File file;
            if (globalConfig.isTest()){
                file = new File( System.getProperty("user.dir") + "//target//classes//"+type+"//"+subtype+"//"+filename+"."+ending);
            }else{
                file = new File("WEB-INF/classes/"+type+"/"+subtype+"/"+filename+"."+ending);
            }
            in = new BufferedReader(new FileReader(file));
            String line = in.readLine();
            String result ="";
            while (line != null){
                result += line;
                System.out.println(line);
                line = in.readLine();
            }
            in.close();
            return result;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
