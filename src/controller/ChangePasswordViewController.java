package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import implementationmodel.UserAccountDOA;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import object.AlertBox;
import object.User;
import object.UserAccount;

/**
 * This controller is the one controlling the change password view
 */
public class ChangePasswordViewController extends Controller implements Initializable {

    @FXML
    private PasswordField oldPass;

    @FXML
    private PasswordField newPass;

    @FXML
    private PasswordField confirmPass;
    
    @FXML
    private TextField username;

    /**
     * When this button is clicked, the password of the user gets updated
     * @param event ActionEvent associated with the button click
     */
    public void updateBtn(ActionEvent event) {
        UserAccountDOA db = UserAccountDOA.GetInstance();
        if(username.getText().length() == 0) {
            new AlertBox("Username Field Empty!");
            return;
        }
        else if(!username.getText().equalsIgnoreCase(User.GetInstance().getUserName())) {
            if(db.Exists(username.getText())) {
                new AlertBox("Username already exists!");
                return;
            }
        }
        if(User.GetInstance().getPassword().equals(oldPass.getText())) {
            if(newPass.getText().equals(confirmPass.getText())) {
                if(newPass.getText().length() > 0) {
                    User.GetInstance().setPassword(newPass.getText());
                    db.ChangeUsernameAndPassword(User.GetInstance().getUserName(), username.getText(), newPass.getText());
                } else {
                    db.ChangeUsername(User.GetInstance().getUserName(), username.getText());
                }
                User.GetInstance().setUserName(username.getText());
                LoadFXML(event, "Calendar App - CalendarView", "/fxml/CalendarView.fxml");
            } else {
                new AlertBox("Passwords Don't Match!");
            }
        } else {
            new AlertBox("Wrong Password!");
        }
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        username.setText(User.GetInstance().getUserName());
    }
}
