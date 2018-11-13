
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static Stage stage;

    @Override
    public void start(Stage primaryStage) throws java.io.IOException {
        stage = primaryStage;
        stage.setResizable(false);
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/CreateAppointmentView.fxml"));
        primaryStage.setTitle("Calendar App - Main Menu");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {

        launch(args);
    }
}
