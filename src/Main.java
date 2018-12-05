
import helper.DateTimeParser;
import helper.Email;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import object.Appointment;
import object.User;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

public class Main extends Application {

    private static Stage stage;

    @Override
    public void start(Stage primaryStage) throws java.io.IOException {
        stage = primaryStage;
        stage.setResizable(false);
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/MainMenuView.fxml"));
        primaryStage.setTitle("Calendar App - Main Menu");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        Thread thread = new Thread() {
            public void run() {
                SettleReminders();
            }
        };
        thread.start();
        launch(args);
    }

    public static void SettleReminders() {
        List<Appointment> alreadyEmailed = new ArrayList<>();
        while(true) {
            List<Appointment> appointments = User.GetInstance().getAppointments();
            String username = User.GetInstance().getUserName();
            String from, pass, to, sub, body;
            from = null;
            pass = null;
            to = null;
            sub = null;
            body = null;
            try {
                for (Appointment app : appointments) {
                    if (alreadyEmailed.contains(app))
                        continue;
                    if (app.getUsername().equals(username)) {
                        if (app.getTime().getTime() - System.currentTimeMillis() < app.getReminder().getTime()) {
                            from = "MY EMAIL";
                            to = User.GetInstance().getEmail();
                            pass = "MY PASSWORD";
                            sub = "Appointment Reminder With: " + app.getWithperson();
                            body = app.getDate() + " @ " + DateTimeParser.getHourMinFromDate(app.getTime());
                            Email.sendEmail(from, pass, to, sub, body);
                            alreadyEmailed.add(app);
                            System.out.println("Emailed Appointment\n" + app);
                        }
                    }
                }
            } catch(ConcurrentModificationException e) {
                e.printStackTrace();
            }
        }
    }
}
