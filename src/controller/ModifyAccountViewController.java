package controller;

import implementationmodel.UserAccountDOA;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import object.User;
import object.UserAccount;

/**
 * This controller is associated with the modify account view controller
 */
public class ModifyAccountViewController extends Controller implements Initializable {
    
    @FXML
    private TextField firstnameField;
    
    @FXML
    private TextField lastnameField;
    
    @FXML
    private TextField usernameField;

    @FXML
    private TextField emailField;
    
    @FXML
    private TextField genderField;
    
    @FXML
    private DatePicker dobField;
    /**
     * When button is clicked, the Change Password View will load
     * @param event ActionEvent associated with the button click
     */
    public void changePasswordBtnPressed(ActionEvent event) {
        LoadFXML(event, "Calendar App - Change Password", "/fxml/ChangePasswordView.fxml");
    }
    
    public void makeChangeBtn(ActionEvent event) {
        UserAccount user = new UserAccount(usernameField.getText(), 
                firstnameField.getText(), lastnameField.getText(), 
                emailField.getText(), 
                genderField.getText().charAt(0), 
                Date.valueOf(dobField.getValue()),
                User.GetInstance().getPassword()
        );
        User.GetInstance().setUserAccount(user);
        UserAccountDOA.GetInstance().Update(user);
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
    
    public void calendarViewBtn(ActionEvent event) {
        LoadFXML(event, "Calendar App - CalendarView", "/fxml/CalendarView.fxml");
    }
    
    public void mainMenuBtn(ActionEvent event) {
        LoadFXML(event, "Calendar App - Main Menu", "/fxml/MainMenuView.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        User u = User.GetInstance();
        setFields(u.getFirstName(), u.getLastName(), u.getUserName(), u.getEmail(), Character.toString(u.getGender()), u.getDateOfBirth());
        usernameField.setEditable(false);
    }
    
    private void setFields(String first, String last, String username, String email, String gender, Date date) {
        usernameField.setText(username);
        lastnameField.setText(last);
        firstnameField.setText(first);
        emailField.setText(email);
        genderField.setText(gender);
        dobField.setValue(date.toLocalDate());
    }

    public void modifyApptBtn(ActionEvent event) {
        LoadFXML(event, "Calendar App - Modify Appointments", "/fxml/ModifyAppointmentView.fxml");
    }
}
