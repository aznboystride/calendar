package controller;

import javafx.event.ActionEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class WeeklyViewController extends CalendarViewController {

    @Override
    protected void initializeGridPaneCells() {

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
