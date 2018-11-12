package controller;

import helper.CalendarHelper;
import helper.CalendarPoint;
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
                p = getCalendarPane(l);
                gridPane.add(p, col, row);
            }
        }
    }

    @Override
    protected void setGridPaneConstraints() {

    }

    protected Label getCalendarLabel(int x, int y) {
        Label label = new Label();
        label.setLayoutX(x);
        label.setLayoutY(y);
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
        setCalendarRange(month.getPromptText(), Integer.valueOf(year.getPromptText()));
        setGridPaneConstraints();
    }

    public void weekBtn(ActionEvent event) {

    }

    @Override
    protected void setCalendarRange(String month, int year) {
        clearCalendarRange(gridPane);
        int lastDayOfMonth = CalendarHelper.getLastDayOfMonth(month, year);
        int firstDayOfWeek = CalendarHelper.getFirstDayOfWeek(month, year);
        for (Node node : gridPane.getChildren()) {
            if (node instanceof Pane) {
                for(Node p : ((Pane) node).getChildren()) {
                    if (p instanceof Label) {
                        CalendarPoint.setPoint(node);
                        if(CalendarPoint.compare(firstDayOfWeek) < 0 && GridPane.getRowIndex(node) == 0) {

                        } else if(CalendarPoint.compare(lastDayOfMonth + firstDayOfWeek - 1) > 0) {

                        } else if(GridPane.getRowIndex(node) == 0){
                            ((Label) p).setText(String.valueOf(1 + CalendarPoint.GetDayNumber() - firstDayOfWeek));
                        }
                    }
                }
            }
        }
    }
}
