package helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileHelper {

    public static void removeExtraSpacesFromFile(File f) {
        String content = "";
        try {
            Scanner sc = new Scanner(f);
            while(sc.hasNextLine()) {
                content += (sc.nextLine() + "\n");
            }
            PrintWriter writer = new PrintWriter(f);
            writer.write(content.trim());
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
