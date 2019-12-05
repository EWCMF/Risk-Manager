package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    @FXML
    private void createRiskWindow(ActionEvent event) throws IOException {
        Parent cRisk = FXMLLoader.load(getClass().getResource("Create_Risk_Window.fxml"));
        Scene scene = new Scene(cRisk);
        Stage appStage = new Stage();
        appStage.setScene(scene);
        appStage.setTitle("Create Risk");
        appStage.show();

    }

    public static void changeToMainWindow() throws IOException {
        Parent root = FXMLLoader.load(Controller.class.getResource("Main.fxml"));
        ui.Main.window.setTitle("Risk Manager");
        ui.Main.window.setScene(new Scene(root, 720, 510));
        ui.Main.window.show();
    }
}
