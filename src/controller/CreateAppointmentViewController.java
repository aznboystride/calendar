package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * Responsible for appointment creation view
 */
public class CreateAppointmentViewController {

    @FXML
    private TextField username;

    @FXML
    private TextField event;

    @FXML
    private DatePicker datePicker;

    @FXML
    private ComboBox time;

    /**
     * Adds appointment to database corresponding to current user
     * @param event
     */
    public void createAppointmentBtn(ActionEvent event) {

    }
}
