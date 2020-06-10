package view.parts;

import config.globalConfig;

import java.io.*;

public abstract class Part {

    /**
     * Class to implement a Basic Part
     */
    public Part() {
        super();
    }

    /**
     * Reading a Template in ressources Folder
     *
     * @param type     Represents the Folder in ressources
     * @param subtype  Represents the nested Folders in the Main Folders in ressources
     * @param filename Represents the filenmae
     * @param ending   Represents the filename
     * @return the content of the file
     */
    public String readRessource(String type, String subtype, String filename, String ending) {
        try {
            BufferedReader in;
            File file;
            file = new File(generatePath("classes", type, subtype, filename, ending));
            in = new BufferedReader(new FileReader(file));
            // Reading the full file and saving the content in result
            String line = in.readLine();
            String result = "";
            while (line != null) {
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

    public String generatePath(String mainfolder, String type, String subtype, String filename, String ending) {
        if (globalConfig.isWindows()) {
            if (globalConfig.isTest()) {
                if (subtype != "") {
                    return System.getProperty("user.dir") + "\\target\\" + mainfolder + "\\" + type + "\\" + subtype + "\\" + filename + "." + ending;
                } else {
                    return System.getProperty("user.dir") + "\\target\\" + mainfolder + "\\" + type + "\\" + filename + "." + ending;
                }
            }
            return "";
        } else {
            if (globalConfig.isTest()) {
                if (subtype != "") {
                    // Changed Path format from //target// to /target/ROOT/WEB-INF/
                    return System.getProperty("user.dir") + "/target/ROOT/WEB-INF/" + mainfolder + "/" + type + "/" + subtype + "/" + filename + "." + ending;
                } else {
                    // Changed Path format from //target// to /target/ROOT/WEB-INF/
                    return System.getProperty("user.dir") + "/target/ROOT/WEB-INF/" + mainfolder + "/" + type + "/" + filename + "." + ending;
                }
            } else {
                String[] pathnames;
                File f = new File("/");
                pathnames = f.list();
                for(String pathname: pathnames) {
                    System.out.println("LS: " + pathname);
                }

                if (subtype != "") {
                    return "/home/site/wwwroot/webapps/ROOT/WEB-INF/" + mainfolder + "/" + type + "/" + subtype + "/" + filename + "." + ending;
                } else {
                    return "/home/site/wwwroot/webapps/ROOT/WEB-INF/" + mainfolder + "/" + type + "/" + filename + "." + ending;
                }
            }
        }
    }
}
