package view.parts;

import config.globalConfig;

import java.io.*;

public abstract class Part {

    /**
     * Class to implement an Basic Part
     */
    public Part(){
        super();
    }

    /**
     * Reading in Template in ressources Folder
     * @param type Represents the Folder in ressources
     * @param subtype Represents the nested Folders in the Main Folders in ressources
     * @param filename Represents the filenmae
     * @param ending Represents the filename
     * @return the content of the file
     */
    public String readRessource(String type,String subtype,String filename,String ending){
        try {
            BufferedReader in;
            File file;
            //Changing paths for test or prod on the boolean in config
            if (globalConfig.isTest()){
                if (subtype != ""){
                    file = new File( System.getProperty("user.dir") + "//target//classes//"+type+"//"+subtype+"//"+filename+"."+ending);
                }
                else{
                    file = new File( System.getProperty("user.dir") + "//target//classes//"+type+"//"+filename+"."+ending);
                }
            }else{
                if (subtype != ""){
                    file = new File("WEB-INF/classes/"+type+"/"+subtype+"/"+filename+"."+ending);
                }
                else{
                    file = new File("WEB-INF/classes/"+type+"/"+filename+"."+ending);
                }
            }
            in = new BufferedReader(new FileReader(file));
            // Reading the full file and saving the content in result
            String line = in.readLine();
            String result ="";
            while (line != null){
                result += line;
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
