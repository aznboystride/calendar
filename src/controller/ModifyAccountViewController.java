package controller;

import javafx.event.ActionEvent;

/**
 * This controller is associated with the modifyaccount view
 */
public class ModifyAccountViewController extends Controller {

    /**
     * When button is clicked, the Change Password View will load
     * @param event ActionEvent associated with the button click
     */
    public void changePasswordBtnPressed(ActionEvent event) {
        LoadFXML(event, "Calendar App - Change Password", "/fxml/ChangePasswordView.fxml");
    }
}
