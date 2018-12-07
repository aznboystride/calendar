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

    @FXML
    private ComboBox timeReminder;

    public void modifyAppointmentBtn(ActionEvent events) {
        List<Appointment> list = User.GetInstance().getAppointments();
        for(Appointment a : list) {
            if(a.getWithperson().equals(username.getText()) &&
                    a.getEventName().equals(event.getText()) &&
                    a.getPlace().equals(place.getText())) {
                a.setDate(Date.valueOf(datePicker.getValue()));
                a.setTime(DateTimeParser.parseTimeFromString(
                        time.getValue().toString(), "hh:mm a")
                );
                AppointmentDOA.GetInstance().Update(a);
                UserAccountDOA.GetInstance().Update(User.GetInstance());
                String remStr = timeReminder.getValue().toString();
                a.setReminder(new Time(a.getTime().getTime() - 1000 * 3600 * Integer.parseInt(remStr.substring(0, remStr.indexOf(' ')))));
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
        initTimeReminder();
        initAppList();
    }

    private void initTimeReminder() {
        for(int i = 1; i <= 24; i++) {
            timeReminder.getItems().add(i + " hr_before");
        }
        timeReminder.setValue(timeReminder.getItems().get(0));
    }

    private void initAppList() {
        List<Appointment> list = User.GetInstance().getAppointments();
        Collections.sort(list);
        appointmentList.getItems().clear();
        for(Appointment app : list) {
            appointmentList.getItems().add(app.getDateUser());
        }
        Appointment a = list.get(0);
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
            if(app.getDateUser().equals(appointmentList.getValue().toString())) {
                User.GetInstance().getAppointments().remove(app);
                appointmentList.getItems().remove(app.getDateUser());
                appointmentList.setValue(appointmentList.getItems().get(0));
                break;
            }
        }
    }

    public void apptListBtn(ActionEvent events) {
        String val = "";
        try {
            val = appointmentList.getValue().toString();
        } catch (Exception e) {
            val = appointmentList.getItems().get(0).toString();
        }
        int firstSpaceIndex = val.indexOf(' ');
        Date date = DateTimeParser.parseDateFromString(val.substring(0, firstSpaceIndex), "yyyy-MM-dd");
        Time _time = DateTimeParser.parseTimeFromString(val.substring(firstSpaceIndex+1, val.lastIndexOf(' ')), "hh:mm a");
        String name = val.substring(val.lastIndexOf(' ') + 1, val.length());
        datePicker.setValue(date.toLocalDate());
        time.setValue(DateTimeParser.getHourMinFromDate(_time));
        username.setText(name);
        for(Appointment a : User.GetInstance().getAppointments()) {
            if(a.getDate().equals(date) && a.getTime().equals(_time) && name.equals(a.getWithperson())) {
                event.setText(a.getEventName());
                place.setText(a.getPlace());
                break;
            }
        }
    }
}
