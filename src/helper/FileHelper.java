package helper;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.FileChooser;

import java.io.*;
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

    public static File ChooseFileOpen(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Import Schedule");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("All Files", "*"));
        return fileChooser.showOpenDialog(((Node) event.getSource()).getScene().getWindow());
    }

    public static File ChooseFileSave(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Import Schedule");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("All Files", "*"));
        return fileChooser.showSaveDialog(((Node) event.getSource()).getScene().getWindow());
    }

    public static void SaveFile(ActionEvent event, String content) {
        File file = ChooseFileSave(event);
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
