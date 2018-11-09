package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
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
                label = new Label("1");
                label.setPadding(new Insets(10,10,10,10));
                label.setFont(Font.font("verdana", 10));
                gridPane.add(label, col, row);
                GridPane.setHalignment(label, HPos.RIGHT);
            }
            gridPane.getColumnConstraints().add(new ColumnConstraints(80));
            if(col == 6)
                continue;
            gridPane.getRowConstraints().add(new RowConstraints(42));
        }
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setGridLinesVisible(true);
        return gridPane;
    }
}
