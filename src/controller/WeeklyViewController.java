package controller;

import javafx.event.ActionEvent;
import javafx.scene.input.KeyEvent;

public class WeeklyViewController extends CalendarViewController {

    public void signOffBtn(ActionEvent event) {
        LoadFXML(event, "Calendar App - Main Menu", "/fxml/MainMenuView.fxml");
    }

    public void weekBtn(ActionEvent event) {

    }

    public void setCalendarColor(ActionEvent event) {

    }

    public void keyTypedMonth(KeyEvent keyEvent) {

    }

    public void monthBtn(ActionEvent event) {

    }

    public void yearBtn(ActionEvent event) {

    }

    @Override
    protected void initializeGridPaneCells() {

    }
}
