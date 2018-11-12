package controller;

import helper.CalendarHelper;
import helper.CalendarPoint;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ResourceBundle;

public class WeeklyViewController extends CalendarViewController {

    @FXML
    private GridPane gridPane;

    @FXML
    private ComboBox weekCombo;

    /**
     * Initializes weekly view gridpane
     */
    @Override
    protected void initializeGridPaneCells() {
        Label l;
        Pane p;
        for(int col = 0; col < 7; col++) {
            for(int row = 0; row < 5; row++) {
                l = getCalendarLabel(80, 5);
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
        initializeWeekCombo();
        setCalendarRange(month.getPromptText(), Integer.valueOf(year.getPromptText()), Integer.parseInt(Character.toString(weekCombo.getPromptText().charAt(5))));
        setGridPaneConstraints();
    }

    public void weekBtn(ActionEvent event) {
        setCalendarRange(monthLabel.getText(), Integer.parseInt(year.getPromptText()), Integer.parseInt(Character.toString(weekCombo.getValue().toString().charAt(5))));
        weekCombo.setPromptText(weekCombo.getValue().toString());
    }

    /**
     * Sets calendar range of first week
     * @param month String month associated with calendar view
     * @param year int year associated with calendar view
     * @param week int week associated with weekly view
     */
    protected void setCalendarRange(String month, int year, int week) {
        clearCalendarRange(gridPane);
        int lastDayOfMonth = CalendarHelper.getLastDayOfMonth(month, year);
        int lastDayOfLastMonth = CalendarHelper.getLastDayOfLastMonth(month, year);
        int firstDayOfWeek = CalendarHelper.getFirstDayOfWeek(month, year);
        for (Node node : gridPane.getChildren()) {
            if (node instanceof Pane) {
                for(Node p : ((Pane) node).getChildren()) {
                    if (p instanceof Label) {
                        CalendarPoint.setPoint(node);
                        CalendarPoint.setY(0);
                        if(CalendarPoint.compare(firstDayOfWeek) < 0 && GridPane.getRowIndex(node) == 0) {
                            CalendarPoint.setPoint(node);
                            ((Label) p).setText(String.valueOf(((1 + CalendarPoint.GetDayNumber() + lastDayOfLastMonth - firstDayOfWeek + 7 * (week-1)) % lastDayOfLastMonth) == 0 ? lastDayOfLastMonth : ((1 + CalendarPoint.GetDayNumber() + lastDayOfLastMonth - firstDayOfWeek + 7 * (week-1)) % lastDayOfLastMonth)));
                            ((Label) p).setStyle("-fx-text-fill: gray");
                        } else if(CalendarPoint.compare(lastDayOfMonth + firstDayOfWeek - 1) > 0 && GridPane.getRowIndex(node) == 0) {
                            CalendarPoint.setPoint(node);
                            ((Label) p).setText(String.valueOf((week-1) * 7 + CalendarPoint.GetDayNumber() - lastDayOfMonth - firstDayOfWeek + 1));
                            ((Label) p).setStyle("-fx-text-fill: gray");
                        } else if(GridPane.getRowIndex(node) == 0){
                            CalendarPoint.setPoint(node);
                            ((Label) p).setText(String.valueOf((week-1) * 7 + 1 + CalendarPoint.GetDayNumber() - firstDayOfWeek));
                        }
                    }
                }
            }
        }
    }

    /**
     * Adds appointment to grid
     * @param title name of appointment
     * @param day day of the week in integer
     */
    private void addAppointment(String title, int day) {

    }

    /**
     * Initializes week combo box
     */
    private void initializeWeekCombo() {
        for(short i = 0; i < 4; i++) {
            weekCombo.getItems().add("Week " + (i+1));
        }
    }

    /**
     * Sets calendar range according the year chosen
     * @param event
     */
    @Override
    public void yearBtn(ActionEvent event) {
        year.setPromptText(year.getValue().toString());
        setCalendarRange(monthLabel.getText(), Integer.parseInt(year.getPromptText()), Integer.parseInt(Character.toString(weekCombo.getPromptText().charAt(5))));
    }

    /**
     * Sets the calendar range according the month chosen
     * @param event
     */
    @Override
    public void monthBtn(ActionEvent event) {
        month.setPromptText(month.getValue().toString());
        monthLabel.setText(month.getValue().toString());
        setCalendarRange(monthLabel.getText(), Integer.parseInt(year.getPromptText()), Integer.parseInt(Character.toString(weekCombo.getPromptText().charAt(5))));
    }
}
