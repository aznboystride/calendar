package controller;


import helper.DateTimeParser;
import implementationmodel.AppointmentDOA;
import implementationmodel.UserAccountDOA;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import object.Appointment;
import object.User;

public class ModifyAppointmentViewController extends Controller implements Initializable {

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

    public void modifyAppointmentBtn(ActionEvent events) {
        List<Appointment> list = User.GetInstance().getAppointments();
        for(Appointment a : list) {
            if(a.getWithperson().equals(username.getText()) &&
                    a.getEventName().equals(event.getText()) &&
                    a.getPlace().equals(place.getText())) {
                list.get(list.indexOf(a)).setDate(Date.valueOf(datePicker.getValue()));
                list.get(list.indexOf(a)).setTime(DateTimeParser.parseTimeFromString(
                        time.getValue().toString(), "hh:mm a")
                );
                AppointmentDOA.GetInstance().Update(list.get(list.indexOf(a)));
                UserAccountDOA.GetInstance().Update(User.GetInstance());
                initAppList();
                break;
            }
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
        initTime();
        initAppList();
    }

    private void initAppList() {
        List<Appointment> list = AppointmentDOA.GetInstance().GetList();
        Collections.sort(list);
        appointmentList.getItems().clear();
        for(Appointment app : list) {
            appointmentList.getItems().add(app.getDateUser());
        }
        Appointment a = list.get(0);
        appointmentList.setValue(a.getDate() + " " + a.getWithperson());
        username.setText(a.getWithperson());
        username.setDisable(true);
        event.setText(a.getEventName());
        event.setDisable(true);
        datePicker.setValue(a.getDate().toLocalDate());
        place.setText(a.getPlace());
        place.setDisable(true);
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
        LoadFXML(event, "Calendar App - Modify Account", "/fxml/ModifyAccountView.fxml");
    }

    public void signOffBtn(ActionEvent event) {
        LoadFXML(event, "Calendar App - Main Menu", "/fxml/MainMenuView.fxml");
    }

    public void changePasswordBtn(ActionEvent event) {
        LoadFXML(event, "Calendar App - Change Password", "/fxml/ChangePasswordView.fxml");
    }

    public void calendarBtn(ActionEvent event) {
        LoadFXML(event, "Calendar App - CalendarView", "/fxml/CalendarView.fxml");
    }

    public void deleteApptBtn(ActionEvent event) {
        AppointmentDOA.GetInstance().Delete(username.getText(), Date.valueOf(datePicker.getValue()), DateTimeParser.parseTimeFromString(time.getValue().toString(), "hh:mm a"));
        for(Appointment app : User.GetInstance().getAppointments()) {
            if(app.getWithperson().equals(username.getText()) &&
                    app.getDate().equals(Date.valueOf(datePicker.getValue())) &&
                    app.getTime().equals(DateTimeParser.parseTimeFromString(time.getValue().toString(), "hh:mm a"))) {
                User.GetInstance().getAppointments().remove(app);
                initAppList();
                break;
            }
        }
    }

    public void apptListBtn(ActionEvent events) {
        for(Appointment app : User.GetInstance().getAppointments()) {
            if(app.getDateUser().equals(appointmentList.getValue().toString())) {
                System.out.println("THIS IS: " + app.getWithperson());
                username.setText(app.getWithperson());
                event.setText(app.getEventName());
                place.setText(app.getPlace());
                datePicker.setValue(app.getDate().toLocalDate());
                time.setValue(app.getTime());
                break;
            }
        }
    }
}
