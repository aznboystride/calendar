package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class CalendarController extends Controller implements Initializable {

    @FXML
    private BorderPane borderPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        GridPane gridPane = getCalendarGridPane();
        borderPane.setCenter(gridPane);
    }

    private GridPane getCalendarGridPane() {
        GridPane gridPane = new GridPane();
        Label label;
        for(int col = 0; col < 7; col++) {
            for(int row = 0; row < 6; row++) {
                label = new Label("N");
                gridPane.add(label, col, row);
            }
        }
        return gridPane;
    }
}
