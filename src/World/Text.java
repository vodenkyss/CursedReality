package World;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Text {

    /**
     * Method for reading text file.
     */
    public static void loadFile(){
        try(BufferedReader br = new BufferedReader(new FileReader("Text.txt"))) {
            String line;
            while((line = br.readLine())!=null){
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("jejda neco se pokazilo");
        }

    }
}
