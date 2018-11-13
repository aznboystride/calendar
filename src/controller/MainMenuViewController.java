package controller;

import implementationmodel.AppointmentDOA;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import implementationmodel.UserAccountDOA;
import java.util.ArrayList;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import object.AlertBox;
import object.User;
import object.UserAccount;
import java.util.List;
import object.Appointment;

/**
 * This is the main menu controller associated with the view
 * where the user can choose to log in or
 * create an account
 */
public class MainMenuViewController extends Controller {

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    /**
     * Loads the account creation view
     * @param event
     */
    public void createAccountBtn(ActionEvent event) {
        LoadFXML(event, "Calendar App - Account Creation", "/fxml/AccountCreationView.fxml");
    }

    /**
     * Triggers when user click login button
     * @param event
     */
    public void loginBtn(ActionEvent event) {
        login(event);
    }

    /**
     * User logs in and loads the Calendar View
     * @param event
     */
    private void login(Event event) {
        UserAccountDOA db = UserAccountDOA.GetInstance();
        List<UserAccount> users = db.GetList();
        for(UserAccount user: users) {
            if(user.getUserName().equalsIgnoreCase(username.getText().trim())) {
                if(user.getPassword().equals(password.getText())) {
                    User.GetInstance().setUserAccount(user);
                    User.GetInstance().setAppointments((ArrayList<Appointment>) AppointmentDOA.GetInstance().GetList());
                    System.out.println(User.GetInstance());
                    LoadFXML(event, "Calendar App - CalendarView", "/fxml/CalendarView.fxml");
                    return;
                }
                break;
            }
        }
        new AlertBox("username or password incorrect");
    }

    /**
     * Triggers when enter press while typing on username field
     * @param keyEvent
     */
    public void keyPressOnUsername(KeyEvent keyEvent) {
        if(keyEvent.getCode().equals(KeyCode.ENTER)) {
            login(keyEvent);
        }
    }

    /**
     * Triggers when enter press while typing on password field
     * @param keyEvent
     */
    public void keyPressOnPassword(KeyEvent keyEvent) {
        if(keyEvent.getCode().equals(KeyCode.ENTER)) {
            login(keyEvent);
        }
    }
}
