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

public class MainMenuViewController extends Controller {

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    public void createAccountBtn(ActionEvent event) {
        LoadFXML(event, "Calendar App - Account Creation", "/fxml/AccountCreationView.fxml");
    }

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
