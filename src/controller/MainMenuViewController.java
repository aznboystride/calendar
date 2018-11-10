package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import implementationmodel.UserAccountDOA;
import object.AlertBox;
import object.User;
import object.UserAccount;
import java.util.List;

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
     * User logs in and loads the Calendar View
     * @param event
     */
    public void loginBtn(ActionEvent event) {
        UserAccountDOA db = UserAccountDOA.GetInstance();
        List<UserAccount> users = db.GetList();
        for(UserAccount user: users) {
            if(user.getUserName().equalsIgnoreCase(username.getText().trim())) {
                if(user.getPassword().equals(password.getText())) {
                    User.GetInstance().setUserAccount(user);
                    LoadFXML(event, "Calendar App - CalendarView", "/fxml/Calendar.fxml");
                    return;
                }
                break;
            }
        }
        new AlertBox("username or password incorrect");
    }
}
