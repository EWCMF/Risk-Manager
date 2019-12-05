package ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import persistence.DBFacade;

import java.io.IOException;
import java.sql.SQLException;

public class LoginWindowController {

    public static String user;
    public static String pass;

    @FXML
    private Button Confirm = new Button();

    @FXML
    private PasswordField User = new PasswordField();

    @FXML
    private PasswordField Pass = new PasswordField();

    @FXML
    private void saveUser() throws SQLException, IOException {
        user = User.getText();
        pass = Pass.getText();

        DBFacade.initializeDB();

        if (DBFacade.conneced) {
            changeToMainWindow();
        }
    }

    public void changeToMainWindow() throws IOException {
        Parent root = FXMLLoader.load(MainWindowController.class.getResource("MainWindow.fxml"));
        ui.Main.window.setTitle("Risk Manager - Risks");
        ui.Main.window.setScene(new Scene(root, 720, 510));
        ui.Main.window.show();
    }
}
