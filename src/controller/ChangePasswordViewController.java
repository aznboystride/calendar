package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import implementationmodel.UserAccountDOA;
import object.AlertBox;
import object.User;

public class ChangePasswordViewController extends Controller {

    @FXML
    private PasswordField oldPass;

    @FXML
    private PasswordField newPass;

    @FXML
    private PasswordField confirmPass;

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
