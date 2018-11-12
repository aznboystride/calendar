package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ResourceBundle;

public class WeeklyViewController extends CalendarViewController {

    @FXML
    private GridPane gridPane;

    @Override
    protected void initializeGridPaneCells() {
        Label l;
        Pane p;
        for(int col = 0; col < 7; col++) {
            for(int row = 0; row < 5; row++) {
                l = getCalendarLabel();
                l.setText("t");
                p = getCalendarPane(l);
                gridPane.add(p, col, row);
            }
        }
    }

    @Override
    protected void setGridPaneConstraints() {

    }

    @Override
    protected Label getCalendarLabel() {
        Label label = new Label();
        label.setLayoutX(70);
        label.setLayoutY(5);
        label.setFont(Font.font("verdana", 10));
        return label;
    }

    /**
     * Initializes the calendar gridpane and month dropdown and year dropdown
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeToggleView();
        initializeMonthDropDown();
        initializeYearDropDown();
        initializeGridPaneCells();
        setGridPaneConstraints();
    }

    public void weekBtn(ActionEvent event) {

    }
}
