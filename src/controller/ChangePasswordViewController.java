package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import implementationmodel.UserAccountDOA;
import object.AlertBox;
import object.User;

/**
 * This controller is the one controlling the changepassword view fxml
 */
public class ChangePasswordViewController extends Controller {

    @FXML
    private PasswordField oldPass;

    @FXML
    private PasswordField newPass;

    @FXML
    private PasswordField confirmPass;

    /**
     * When this button is clicked, the password of the user gets updated
     * @param event ActionEvent associated with the button click
     */
    public void updateBtn(ActionEvent event) {
        UserAccountDOA db = UserAccountDOA.GetInstance();
        if(User.GetInstance().getPassword().equals(oldPass.getText())) {
            if(newPass.getText().equals(confirmPass.getText())) {
                User.GetInstance().setPassword(newPass.getText());
                db.Update(User.GetInstance());
                LoadFXML(event, "Calendar App - CalendarView", "/fxml/Calendar.fxml");
            } else {
                new AlertBox("Passwords Don't Match!");
            }
        } else {
            new AlertBox("Wrong Password!");
        }
    }
}
