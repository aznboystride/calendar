package controller;

import helper.CalendarPoint;
import helper.DateTimeParser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import java.net.URL;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;

import helper.CalendarHelper;
import object.Appointment;
import object.User;

/**
 * This class controller is largely responsible for the creation
 * and the manipulation of the calendar view.
 */
public class CalendarViewController extends CalendarController implements Initializable {

    @FXML
    private BorderPane borderPane;

    @FXML
    protected ComboBox year;

    @FXML
    protected ComboBox month;

    @FXML
    protected ColorPicker colorPicker;

    @FXML
    protected Label monthLabel;

    @FXML
    protected RadioButton calendarView;

    @FXML
    protected RadioButton dailyView;

    @FXML
    protected RadioButton weeklyView;

    protected GridPane gridPane;

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
        setCalendarRange(month.getPromptText(), Integer.parseInt(year.getPromptText()));
        setGridPaneConstraints();
        borderPane.setCenter(gridPane);
    }

    /**
     * Initializes all of the radio buttons in toggleview
     */
    protected void initializeToggleView() {
        calendarView.setOnAction(event ->
                LoadFXML(
                        event,
                        "Calendar App - Calendar View",
                        "/fxml/CalendarView.fxml"
                )
        );
        dailyView.setOnAction(event ->
                LoadFXML(
                        event,
                        "Calendar App - Daily View",
                        "/fxml/DailyView.fxml"
                )
        );
        weeklyView.setOnAction(event ->
                LoadFXML(
                        event,
                        "Calendar App - Weekly View",
                        "/fxml/WeeklyView.fxml"
                )
        );
    }

    /**
     * Initializes gridpane cells and add a blank pane to each cell
     */
    protected void initializeGridPaneCells() {
        gridPane = new GridPane();
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

    /**
     * Creates and returns a Label with a default font and size
     * @return Label
     */
    protected Label getCalendarLabel(int x, int y, String id) {
        Label label = new Label();
        label.setId(id);
        label.setLayoutX(x);
        label.setLayoutY(y);
        label.setFont(Font.font("verdana", 10));
        return label;
    }

    /**
     * Takes in a Label and adds appends it to a pane
     * @param label Came from getCalendarLabel method
     * @return new pane with label associated with it
     */
    protected Pane getCalendarPane(Label label, Label label2) {
        Pane pane = new Pane();
        pane.getChildren().addAll(label, label2);
        return pane;
    }

    /**
     * Sets the default constraints of the calendar gridpane
     */
    protected void setGridPaneConstraints() {
        gridPane.setGridLinesVisible(false);
        gridPane.setAlignment(Pos.CENTER);
        ColumnConstraints columnConstraints = new ColumnConstraints(0);
        RowConstraints rowConstraints = new RowConstraints(0);
        rowConstraints.setPercentHeight(50);
        columnConstraints.setPercentWidth(50);
        for (int col = 0; col < 7; col++) {
            gridPane.getColumnConstraints().add(columnConstraints);
            if (col == 6)
                break;
            gridPane.getRowConstraints().add(rowConstraints);
        }
    }

    /**
     * Initializes the year drop down all the way to 1920
     */
    protected void initializeYearDropDown() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(System.currentTimeMillis()));
        year.setPromptText(Integer.toString(calendar.get(Calendar.YEAR)));
        for (int i = 2025; i >= 1920; i--) {
            year.getItems().add(i);
        }
    }

    /**
     * Initializes the month drop down
     */
    protected void initializeMonthDropDown() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM");
        month.setPromptText(dateFormat.format(new Date(System.currentTimeMillis())));
        monthLabel.setText(month.getPromptText());
        for (int i = 1; i <= 12; i++) {
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("M");
                month.getItems().add(new SimpleDateFormat("MMMM").format(simpleDateFormat.parse(String.valueOf(i))));
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * This method sets the calendar according to the month and year
     * @param month String month associated with calendar view
     * @param year int year associated with calendar view
     */
    protected void setCalendarRange(String month, int year) {
        clearCalendarRange(gridPane);
        int lastDayOfMonth = CalendarHelper.getLastDayOfMonth(month, year);
        int lastDayOfLastMonth = CalendarHelper.getLastDayOfLastMonth(month, year);
        int firstDayOfWeek = CalendarHelper.getFirstDayOfWeek(month, year);
        for (Node node : gridPane.getChildren()) {
            if (node instanceof Pane) {
                for(Node p : ((Pane) node).getChildren()) {
                    if (p instanceof Label && ((Label) p).getId().equals("date")) {
                        CalendarPoint.setPoint(node);
                        if(CalendarPoint.compare(firstDayOfWeek) < 0 && GridPane.getRowIndex(node) == 0) {
                            ((Label) p).setText(String.valueOf(1 + CalendarPoint.GetDayNumber() + lastDayOfLastMonth - firstDayOfWeek));
                            ((Label) p).setStyle("-fx-text-fill: gray");
                        } else if(CalendarPoint.compare(lastDayOfMonth + firstDayOfWeek - 1) > 0) {
                            ((Label) p).setText(String.valueOf(CalendarPoint.GetDayNumber() - lastDayOfMonth - firstDayOfWeek + 1));
                            ((Label) p).setStyle("-fx-text-fill: gray");
                        } else {
                            ((Label) p).setText(String.valueOf(1 + CalendarPoint.GetDayNumber() - firstDayOfWeek));
                        }
                    }
                }
            }
        }
        addAppointmentToCalendar();
    }
    
    /**
     * Adds all the appointments to calendar
     */
    protected void addAppointmentToCalendar() {
        for(Node node: gridPane.getChildren()) {
            if(node instanceof Pane) {
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
    

    /**
     * This method clears all the labels and color of calendar Gridpane.
     * Typically called before resetting the Calendar Gridpane.
     */
    protected void clearCalendarRange(GridPane gridPane) {
        for(Node node : gridPane.getChildren()) {
            if(node instanceof Pane) {
                for(Node p : ((Pane) node).getChildren())
                    if (p instanceof Label) {
                        ((Label) p).setText("");
                        ((Label) p).setStyle(null);
                    }
            }
        }
    }

    /**
     * Sets calendar range according the year chosen
     * @param event
     */
    public void yearBtn(ActionEvent event) {
        year.setPromptText(year.getValue().toString());
        setCalendarRange(month.getPromptText(), Integer.parseInt(year.getPromptText()));
    }

    /**
     * Sets the calendar range according the month chosen
     * @param event
     */
    public void monthBtn(ActionEvent event) {
        month.setPromptText(month.getValue().toString());
        monthLabel.setText(month.getValue().toString());
        setCalendarRange(month.getPromptText(), Integer.parseInt(year.getPromptText()));
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
    public void setCalendarColor(ActionEvent event) {
        gridPane.setStyle(String.format("-fx-background-color: #%s", Integer.toHexString(colorPicker.getValue().hashCode())));
    }

    /**
     * Sets month combobox as user types
     * @param keyEvent
     */
    public void keyTypedMonth(KeyEvent keyEvent) {
        for(Object m : month.getItems()) {
            if(m.toString().startsWith(keyEvent.getCharacter().toUpperCase())) {
                month.setValue(m);
                monthLabel.setText(m.toString());
            }
        }
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
}
