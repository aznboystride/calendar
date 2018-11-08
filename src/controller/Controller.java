package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.io.IOException;

public abstract class Controller {

    protected void LoadFXML(ActionEvent event, String title, String fxml) {
        Stage window = null;
        if(event.getSource() instanceof Node) {
            window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        } else if(event.getSource() instanceof MenuItem) {
            window = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        } else {
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        window.setTitle(title);
        try {
            window.setScene(new Scene(FXMLLoader.load(getClass().getResource(fxml))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        window.show();
    }
}
