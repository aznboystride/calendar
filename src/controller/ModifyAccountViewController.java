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
}
