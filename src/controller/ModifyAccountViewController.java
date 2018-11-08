package controller;

import javafx.event.ActionEvent;

public class ModifyAccountViewController extends Controller {

    public void changePasswordBtnPressed(ActionEvent event) {
        LoadFXML(event, "Calendar App - Change Password", "/fxml/ChangePasswordView.fxml");
    }
}
