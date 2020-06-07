package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SecretsReader {
    public static String getSqlPassword(){
        return read(0);
    }

    public static String getSendGridKey(){
        return read(1);
    }

    private static String read(int line){
        List<String> readLines = new ArrayList<String>();
        try {
            File myObj = new File("src/main/java/utilities/secrets.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                readLines.add(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "";
        }

        return readLines.get(line);
    }
}
