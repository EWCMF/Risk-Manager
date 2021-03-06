package ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import persistence.DBFacade;

import java.io.IOException;

public class LoginWindowController {

    @FXML
    private Button Confirm = new Button();

    @FXML
    private PasswordField user = new PasswordField();

    @FXML
    private PasswordField pass = new PasswordField();

    @FXML
    private void saveUser() throws IOException {
        String userInput = user.getText();
        String passInput = pass.getText();

        DBFacade.initializeDB(userInput, passInput);

        if (DBFacade.conneced) {
            changeToMainWindow();
        } else {
            ErrorWindowController error = new ErrorWindowController();
            error.owner = Main.window;
            error.newError("Kan ikke logge ind.");
        }
    }
    public void handle(KeyEvent keyEvent) throws IOException {
        if(keyEvent.getCode() == KeyCode.ENTER)
        {
            saveUser();
        }
    }
    public void changeToMainWindow() throws IOException {
        Parent root = FXMLLoader.load(MainWindowController.class.getResource("MainWindow.fxml"));
        ui.Main.window.setTitle("Risk Manager - Risks");
        ui.Main.window.setScene(new Scene(root, 720, 510));
        ui.Main.window.show();
    }
}
