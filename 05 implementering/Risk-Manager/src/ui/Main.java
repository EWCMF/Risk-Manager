package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static Stage window;

    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;

        Parent root = FXMLLoader.load(getClass().getResource("LoginWindow.fxml"));
        window.setTitle("Risk Manager");
        window.setScene(new Scene(root, 720, 510));
        window.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
