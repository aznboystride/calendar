package controller;


import implementationmodel.AppointmentDOA;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import object.Appointment;

public class ModifyAppointmentViewController implements Initializable {

    @FXML
    private ComboBox appointmentList;

    public void modifyAppointmentBtn(ActionEvent event) {
        List<Appointment> list = AppointmentDOA.GetInstance().GetList();
        for(Appointment app : list) {
            appointmentList.getItems().add(app.getDate() + " " + app.getWithperson());
        }
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

    public void modifyAccountBtn(ActionEvent event) {

    }

    public void signOffBtn(ActionEvent event) {

    }

    public void changePasswordBtn(ActionEvent event) {

    }

    public void calendarBtn(ActionEvent event) {
        
    }
}
