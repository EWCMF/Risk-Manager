package ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ErrorWindowController {
    static String message;
    Stage owner;

    @FXML
    Label label;
    @FXML
    Button button;

    public void newError(String message) throws IOException {
        ErrorWindowController.message = message;

        Parent root = FXMLLoader.load(getClass().getResource("ErrorWindow.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.setTitle("Fejl");
        stage.setResizable(false);
        stage.initOwner(owner);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.show();
    }

    @FXML
    void initialize() {
        label.setText(message);
    }

    @FXML
    void close() {
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }
}
