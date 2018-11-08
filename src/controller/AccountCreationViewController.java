package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import implementationmodel.UserAccountDOA;
import object.AlertBox;
import object.User;
import object.UserAccount;

import java.sql.Date;

public class AccountCreationViewController extends Controller {

    @FXML
    private VBox accountFields;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private TextField genderField;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField firstnameField;

    @FXML
    private TextField lastnameField;

    @FXML
    private TextField emailField;

    @FXML
    private DatePicker dob;

    public void createUserBtn(ActionEvent event) {
        if(!checkEmptyField()) {
            new AlertBox("You Left Some Fields Empty!");
        } else if(!passwordField.getText().equals(confirmPasswordField.getText())) {
            new AlertBox("Passwords Do Not Match!");
        } else if (genderField.getText().equalsIgnoreCase("M") && genderField.getText().equalsIgnoreCase("F")) {
            new AlertBox("Gender Field Must Be M / F");
        } else {
            trimAllSpaces();
            UserAccount userAccount = new UserAccount(usernameField.getText(), firstnameField.getText(),
                    lastnameField.getText().trim(), emailField.getText().trim(), genderField.getText().charAt(0),
                    Date.valueOf(dob.getValue()), passwordField.getText());
            if(!UserAccountDOA.GetInstance().Exists(userAccount)) {
                User.GetInstance().setUserAccount(userAccount);
                UserAccountDOA.GetInstance().Insert(userAccount);
                LoadFXML(event, "Calendar App - Main Menu", "/fxml/MainMenuView.fxml");
            } else {
                new AlertBox("That username already exists!");
            }
        }
    }

    private void trimAllSpaces() {
        for(Node node: accountFields.getChildren()) {
            if(node instanceof TextField) {
                ((TextField) node).setText(((TextField) node).getText().trim());
            }
        }
    }

    private boolean checkEmptyField() {
        for(Node node: accountFields.getChildren()) {
            if(node instanceof TextField) {
                if(((TextField) node).getText().length() == 0)
                    return false;
            } else if(node instanceof PasswordField) {
                if(((PasswordField) node).getText().length() == 0)
                    return false;
            } else {
                if(((DatePicker) node).getValue() == null) {
                    return false;
                }
            }
        }
        return true;
    }
}
