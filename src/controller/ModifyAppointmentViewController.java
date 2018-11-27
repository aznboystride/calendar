package controller;


import helper.DateTimeParser;
import implementationmodel.AppointmentDOA;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import object.Appointment;

public class ModifyAppointmentViewController implements Initializable {

    @FXML
    private ComboBox appointmentList;

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

    public void modifyAppointmentBtn(ActionEvent event) {

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
        initTime();
        initAppList();
    }

    private void initAppList() {
        List<Appointment> list = AppointmentDOA.GetInstance().GetList();
        for(Appointment app : list) {
            appointmentList.getItems().add(app.getDate() + " " + app.getWithperson());
        }
        Appointment a = list.get(0);
        appointmentList.setValue(a.getDate() + " " + a.getWithperson());
        username.setText(a.getWithperson());
        event.setText(a.getEventName());
        datePicker.setValue(a.getDate().toLocalDate());
        place.setText(a.getPlace());
        time.setValue(DateTimeParser.getHourMinFromDate(a.getTime()));
    }

    private void initTime() {
        Time twelveAM = DateTimeParser.parseTimeFromString("12:00 PM", "hh:mm a");
        time.getItems().add(DateTimeParser.getHourMinFromDate(twelveAM));
        for(int i = 0; i < 47; i++) {
            twelveAM = new Time(twelveAM.getTime() + 1000 * 60 * 30);
            time.getItems().add(DateTimeParser.getHourMinFromDate(twelveAM));
        }
    }

    public void modifyAccountBtn(ActionEvent event) {

    }

    public void signOffBtn(ActionEvent event) {

    }

    public void changePasswordBtn(ActionEvent event) {

    }

    public void calendarBtn(ActionEvent event) {

    }

    public void deleteApptBtn(ActionEvent event) {

    }
}
