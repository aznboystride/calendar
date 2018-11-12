package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class WeeklyViewController extends CalendarViewController {

    @FXML
    private GridPane gridPane;

    @Override
    protected void initializeGridPaneCells() {
        Label l;
        for(int col = 0; col < 7; col++) {
            for(int row = 0; row < 5; row++) {
                l = new Label("t");
                gridPane.add(l, col, row);
                System.out.println(col + " " + row);
            }
        }
    }

    @Override
    protected void setGridPaneConstraints() {

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
