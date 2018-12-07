package controller;

import helper.DateTimeParser;
import helper.FileHelper;
import implementationmodel.AppointmentDOA;
import java.io.File;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import object.AlertBox;
import object.Appointment;
import object.User;

import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Time;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

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
                DateTimeParser.parseTimeFromString(time.getValue().toString(), "hh:mm a"),
                place.getText(),
                event.getText(),
                User.GetInstance().getUserName(),
                username.getText()
        );
        List<Appointment> list = User.GetInstance().getAppointments();
        for(Appointment app : list) {
            if(app.getDate().compareTo(appointment.getDate()) == 0 && app.getDate().getYear() == appointment.getDate().getYear()) {
                new AlertBox("Appointment is too close to existing appointment!");
                return;
            }
        }
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
        initTime();
    }

    private void initTime() {
        Time twelveAM = DateTimeParser.parseTimeFromString("12:00 PM", "hh:mm a");
        time.getItems().add(DateTimeParser.getHourMinFromDate(twelveAM));
        for(int i = 0; i < 47; i++) {
            twelveAM = new Time(twelveAM.getTime() + 1000 * 60 * 30);
            time.getItems().add(DateTimeParser.getHourMinFromDate(twelveAM));
        }
        time.setValue(time.getItems().get(0));
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

    public void importScheduleBtn(ActionEvent event) {
        File selectedFile = FileHelper.ChooseFileOpen(event);
        if(selectedFile != null) {
            AppointmentDOA.GetInstance().Delete(User.GetInstance().getUserName());
            List<Appointment> appointmentList = parseAppointmentsFromFile(selectedFile);
            for(Appointment app : appointmentList) {
                AppointmentDOA.GetInstance().Insert(app);
            }
            User.GetInstance().setAppointments((ArrayList<Appointment>)appointmentList);
        }
    }

        public void exportScheduleBtn(ActionEvent event) {
        String content = "";
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        SimpleDateFormat stf = new SimpleDateFormat("hh:mm a");
        List<Appointment> appointmentList = AppointmentDOA.GetInstance().GetList();
        for(Appointment app : appointmentList) {
            if(app.getUsername().equalsIgnoreCase(User.GetInstance().getUserName())) {
                content += (app.getWithperson() + "\n");
                content += (app.getPlace() + "\n");
                content += (app.getEventName() + "\n");
                content += (sdf.format(app.getDate()) + "\n");
                content += ((stf.format(new Date(app.getTime().getTime()))) + "\n");
            }
        }
        FileHelper.SaveFile(event, content);
    }

    private List<Appointment> parseAppointmentsFromFile(File selectedFile) {
        FileHelper.removeExtraSpacesFromFile(selectedFile);
        String username, withPerson, place, event, sdate, stime;
        Time time;
        Date date;
        List<Appointment> appointmentList = new ArrayList<>();

        try {
            Scanner sc = new Scanner(selectedFile);
            username = User.GetInstance().getUserName();
            while(sc.hasNextLine()) {
                withPerson = sc.nextLine();
                place = sc.nextLine();
                event = sc.nextLine();
                sdate = sc.nextLine();
                stime = sc.nextLine();
                time = DateTimeParser.parseTimeFromString(stime, "hh:mm a");
                date = DateTimeParser.parseDateFromString(sdate, "MM/dd/yyyy");
                appointmentList.add(new Appointment(date, time, place, event, username, withPerson));
            }
            return appointmentList;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void modifyApptBtn(ActionEvent event) {
        LoadFXML(event, "Calendar App - Modify Appomentsint", "/fxml/ModifyAppointmentView.fxml");
    }
}
