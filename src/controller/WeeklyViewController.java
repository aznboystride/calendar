package controller;

import helper.CalendarHelper;
import helper.CalendarPoint;
import helper.DateTimeParser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.geometry.HPos;
import javafx.scene.control.ColorPicker;
import object.Appointment;
import object.User;

public class WeeklyViewController extends CalendarViewController {

    @FXML
    private GridPane gridPane;

    @FXML
    private ComboBox weekCombo;
    
    @FXML
    private ColorPicker colorPicker;

    /**
     * Initializes weekly view gridpane
     */
    @Override
    protected void initializeGridPaneCells() {
        //gridPane = new GridPane();
        Pane pane;
        Label label;
        Label label2;
        for (int col = 0; col < 7; col++) {
            for (int row = 0; row < 6; row++) {
                label = getCalendarLabel(75, 5, "date");
                label2 = getCalendarLabel(40, 20, "app");
                pane = getCalendarPane(label, label2);
                GridPane.setHalignment(label, HPos.RIGHT);
                GridPane.setHalignment(label2, HPos.LEFT);
                gridPane.add(pane, col, row);
            }
        }
    }

    @Override
    protected void setGridPaneConstraints() {
        gridPane.setGridLinesVisible(false);
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
                    if (p instanceof Label && ((Label) p).getId().equals("date")) {
                        CalendarPoint.setPoint(node);
                        CalendarPoint.setY(0);
                        if(CalendarPoint.compare(firstDayOfWeek) < 0 && GridPane.getRowIndex(node) == 0) {
                            CalendarPoint.setPoint(node);
                            ((Label) p).setText(String.valueOf(((1 + CalendarPoint.GetDayNumber() + lastDayOfLastMonth - firstDayOfWeek + 7 * (week-1)) % lastDayOfLastMonth) == 0 ? lastDayOfLastMonth : ((1 + CalendarPoint.GetDayNumber() + lastDayOfLastMonth - firstDayOfWeek + 7 * (week-1)) % lastDayOfLastMonth)));
                            if(week == 1) ((Label) p).setStyle("-fx-text-fill: gray");
                        } else if(CalendarPoint.compare(lastDayOfMonth + firstDayOfWeek - 1) > 0 && GridPane.getRowIndex(node) == 0) {
                            CalendarPoint.setPoint(node);
                            ((Label) p).setText(String.valueOf(((week-1) * 7 + 1 + CalendarPoint.GetDayNumber() - firstDayOfWeek) % lastDayOfMonth == 0 ? lastDayOfMonth : ((week-1) * 7 + 1 + CalendarPoint.GetDayNumber() - firstDayOfWeek) % lastDayOfMonth));
                            ((Label) p).setStyle("-fx-text-fill: gray");
                        } else if(GridPane.getRowIndex(node) == 0){
                            CalendarPoint.setPoint(node);
                            ((Label) p).setText(String.valueOf(((week-1) * 7 + 1 + CalendarPoint.GetDayNumber() - firstDayOfWeek) % lastDayOfMonth == 0 ? lastDayOfMonth : ((week-1) * 7 + 1 + CalendarPoint.GetDayNumber() - firstDayOfWeek) % lastDayOfMonth));
                        }
                    }
                }
            }
        }
        addAppointmentToCalendar();
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
        for(short i = 0; i < 5; i++) {
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

    public void modifyAccountBtn(ActionEvent event) {
        LoadFXML(event, "Calendar App - Modify Account", "/fxml/ModifyAccountView.fxml");
    }

    public void changePasswordBtn(ActionEvent event) {
        LoadFXML(event, "Calendar App - Change Password", "/fxml/ChangePasswordView.fxml");
    }

    public void createApptBtn(ActionEvent event) {
        LoadFXML(event, "Calendar App - Create Appointment", "/fxml/CreateAppointmentView.fxml");
    }

    /**
     * Signs of the user and loads the main menu
     * @param event
     */
    public void signOffBtn(ActionEvent event) {
        LoadFXML(event, "Calendar App - Main Menu", "/fxml/MainMenuView.fxml");
    }
    
    /**
     * Color picker that allows user to change the color of calendar view
     * @param event
     */
    @Override
    public void setCalendarColor(ActionEvent event) {
        gridPane.setStyle(String.format("-fx-background-color: #%s", Integer.toHexString(colorPicker.getValue().hashCode())));
    }
    
    @Override
    protected void addAppointmentToCalendar() {
        for(Node node: gridPane.getChildren()) {
            if(node instanceof Pane) {
                if(GridPane.getRowIndex(node) != 0)
                    continue;
                Label dateLabel = CalendarHelper.getDateLabel((Pane) node);
                Label appLabel = CalendarHelper.getAppLabel((Pane) node);
                for(Appointment app : User.GetInstance().getAppointments()) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(app.getDate());
                    if(calendar.get(Calendar.DAY_OF_MONTH) == Integer.parseInt(dateLabel.getText()) &&
                            calendar.get(Calendar.YEAR) == Integer.parseInt(year.getPromptText()) &&
                            calendar.get(Calendar.MONTH) == CalendarHelper.getMonthFromString(month.getPromptText()) - 1 &&
                            !dateLabel.getStyle().equals("-fx-text-fill: gray")) {
                        appLabel.setText(app.getWithperson() + "\n" + DateTimeParser.getHourMinFromDate(app.getTime()));
                        appLabel.setStyle("-fx-text-fill: green");
                        break;
                    }
                }
            }
        }
    }
}
