package controller;

import helper.CalendarPoint;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

/**
 * This class controller is largely responsible for the creation
 * and the manipulation of the calendar view.
 */
public class CalendarController extends Controller implements Initializable {

    @FXML
    private BorderPane borderPane;

    @FXML
    private ComboBox year;

    @FXML
    private ComboBox month;

    @FXML
    private ColorPicker colorPicker;

    @FXML
    private Label monthLabel;

    @FXML
    private RadioButton calendarView;

    @FXML
    private RadioButton dailyView;

    @FXML
    private RadioButton weeklyView;

    private GridPane gridPane;

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
    private void initializeToggleView() {
        calendarView.setOnAction(event ->
                LoadFXML(
                        event,
                        "Calendar App - Calendar View",
                        "/fxml/Calendar.fxml"
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
    private void initializeGridPaneCells() {
        gridPane = new GridPane();
        Pane pane;
        Label label;
        for (int col = 0; col < 7; col++) {
            for (int row = 0; row < 6; row++) {
                label = getCalendarLabel();
                pane = getCalendarPane(label);
                GridPane.setHalignment(label, HPos.RIGHT);
                gridPane.add(pane, col, row);
            }
        }
    }

    /**
     * Creates and returns a Label with a default font and size
     * @return Label
     */
    private Label getCalendarLabel() {
        Label label = new Label();
        label.setLayoutX(75);
        label.setLayoutY(5);
        label.setFont(Font.font("verdana", 10));
        return label;
    }

    /**
     * Takes in a Label and adds appends it to a pane
     * @param label Came from getCalendarLabel method
     * @return new pane with label associated with it
     */
    private Pane getCalendarPane(Label label) {
        Pane pane = new Pane();
        pane.getChildren().add(label);
        return pane;
    }

    /**
     * Sets the default constraints of the calendar gridpane
     */
    private void setGridPaneConstraints() {
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
    private void initializeYearDropDown() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(System.currentTimeMillis()));
        year.setPromptText(Integer.toString(calendar.get(Calendar.YEAR)));
        for (int i = 2019; i >= 1920; i--) {
            year.getItems().add(i);
        }
    }

    /**
     * Initializes the month drop down
     */
    private void initializeMonthDropDown() {
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
    private void setCalendarRange(String month, int year) {
        clearCalendarRange();
        int lastDayOfMonth = CalendarHelper.getLastDayOfMonth(month, year);
        int lastDayOfLastMonth = CalendarHelper.getLastDayOfLastMonth(month, year);
        int firstDayOfWeek = CalendarHelper.getFirstDayOfWeek(month, year);
        for (Node node : gridPane.getChildren()) {
            if (node instanceof Pane) {
                for(Node p : ((Pane) node).getChildren()) {
                    if (p instanceof Label) {
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
    }

    /**
     * This method clears all the labels and color of calendar Gridpane.
     * Typically called before resetting the Calendar Gridpane.
     */
    private void clearCalendarRange() {
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
}
