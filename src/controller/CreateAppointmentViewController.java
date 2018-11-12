package controller;

import implementationmodel.AppointmentDOA;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import object.Appointment;
import object.User;

import java.net.URL;
import java.sql.Time;
import java.sql.Date;
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
    private TextField place;

    @FXML
    private DatePicker datePicker;

    @FXML
    private ComboBox time;

    /**
     * Adds appointment to database corresponding to current user
     * @param e
     */
    public void createAppointmentBtn(ActionEvent e) {
        Appointment appointment = new Appointment(
                Date.valueOf(datePicker.getValue()),
                new Time(System.currentTimeMillis()),
                place.getText(),
                event.getText(),
                User.GetInstance().getUserName(),
                username.getText()
        );
        User.GetInstance().createAppointment(appointment);
        AppointmentDOA.GetInstance().Insert(appointment);
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

    /**
     * When button is clicked, the Change Password View will load
     * @param event ActionEvent associated with the button click
     */
    public void changePasswordBtnPressed(ActionEvent event) {
        LoadFXML(event, "Calendar App - Change Password", "/fxml/ChangePasswordView.fxml");
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

    public void calendarBtn(ActionEvent event) {
        LoadFXML(event, "Calendar App - CalendarView", "/fxml/CalendarView.fxml");
    }
}
