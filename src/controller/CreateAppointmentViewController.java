package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Responsible for appointment creation view
 */
public class CreateAppointmentViewController extends Controller implements Initializable {

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


    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
}