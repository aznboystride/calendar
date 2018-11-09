package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import java.net.URL;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;

public class CalendarController extends Controller implements Initializable {

    @FXML
    private BorderPane borderPane;

    @FXML
    private ComboBox year;

    @FXML
    private ComboBox month;

    private GridPane gridPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeMonthDropDown();
        initializeYearDropDown();
        initializeGridPaneCells();
        setCalendarRange(month.getPromptText(), Integer.parseInt(year.getPromptText()));
        setGridPaneConstraints();
        borderPane.setCenter(gridPane);
    }

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

    private Label getCalendarLabel() {
        Label label = new Label();
        label.setLayoutX(75);
        label.setLayoutY(5);
        label.setFont(Font.font("verdana", 10));
        return label;
    }

    private Pane getCalendarPane(Label label) {
        Pane pane = new Pane();
        pane.getChildren().add(label);
        pane.setStyle("-fx-background-color: cornsilk");
        return pane;
    }

    private void setGridPaneConstraints() {
        gridPane.setGridLinesVisible(true);
        gridPane.setAlignment(Pos.CENTER);
        ColumnConstraints columnConstraints = new ColumnConstraints(83);
        RowConstraints rowConstraints = new RowConstraints(42);
        rowConstraints.setPercentHeight(50);
        columnConstraints.setPercentWidth(50);
        for (int col = 0; col < 7; col++) {
            gridPane.getColumnConstraints().add(columnConstraints);
            if (col == 6)
                break;
            gridPane.getRowConstraints().add(rowConstraints);
        }
    }

    private void initializeYearDropDown() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(System.currentTimeMillis()));
        year.setPromptText(Integer.toString(calendar.get(Calendar.YEAR)));
        for (int i = 2019; i >= 1920; i--) {
            year.getItems().add(i);
        }
    }

    private void initializeMonthDropDown() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM");
        month.setPromptText(dateFormat.format(new Date(System.currentTimeMillis())));
        for (int i = 1; i <= 12; i++) {
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("M");
                month.getItems().add(new SimpleDateFormat("MMMM").format(simpleDateFormat.parse(String.valueOf(i))));
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        }
    }

    private int getMonthFromString(String month) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        Date date = new Date(new SimpleDateFormat("MMMM").parse(month).getTime());
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    private int getFirstDayOfWeek(String month, int year) {
        int monthNum = 0;
        try {
            monthNum = getMonthFromString(month);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        Date date = null;
        try {
            date = new Date(new SimpleDateFormat("yyyy-MM-dd").parse(Integer.toString(year) + "-" + Integer.toString(monthNum) + "-01").getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    private int getLastDayOfMonth(String month, int year) {
        int monthNum = 0;
        try {
            monthNum = getMonthFromString(month);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        Date date = null;
        try {
            date = new Date(new SimpleDateFormat("yyyy-M-dd").parse(Integer.toString(year) + "-" + Integer.toString(monthNum) + "-01").getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    private void setCalendarRange(String month, int year) {
        clearCalendarRange();
        int lastDayOfMonth = getLastDayOfMonth(month, year);
        int firstDayOfWeek = getFirstDayOfWeek(month, year);
        System.out.println(month + ": " + year);
        int day = 1;
        for (Node node : gridPane.getChildren()) {
            if (node instanceof Label) {
                if(day == lastDayOfMonth + 1)
                    break;
                if(GridPane.getRowIndex(node) * 7 + GridPane.getColumnIndex(node) + 1 > lastDayOfMonth + firstDayOfWeek)
                    continue;
                if(GridPane.getRowIndex(node) > 0 || GridPane.getColumnIndex(node) >= firstDayOfWeek) {
                    ((Label) node).setText(String.valueOf(GridPane.getRowIndex(node) * 7 + GridPane.getColumnIndex(node) - firstDayOfWeek + 1));
                    day++;
                }
            }
        }
    }

    private void clearCalendarRange() {
        for(Node node : gridPane.getChildren()) {
            if(node instanceof Label) {
                ((Label) node).setText("");
            }
        }
    }

    public void yearBtn(ActionEvent event) {
        year.setPromptText(year.getValue().toString());
        setCalendarRange(month.getPromptText(), Integer.parseInt(year.getPromptText()));
    }

    public void monthBtn(ActionEvent event) {
        month.setPromptText(month.getValue().toString());
        setCalendarRange(month.getPromptText(), Integer.parseInt(year.getPromptText()));
    }
}
