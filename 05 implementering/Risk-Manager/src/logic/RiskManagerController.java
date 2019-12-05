package logic;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class RiskManagerController {
    @FXML
    private PasswordField User = new PasswordField();

    @FXML
    private PasswordField Pass = new PasswordField();

    @FXML
    private Button Confirm = new Button();

    RiskTable rt = new RiskTable();
    StrategyTable st = new StrategyTable();
    private Statement stmt;


    public Risk addRisk() {
        return null;
    }

    public void specifyRisk(Risk risk, String description, double probability, double consequence) {
        rt.specifyRisk(risk, description, probability, consequence);
    }

    public void linkStrategy(Risk risk, Strategy strategy) {

    }

    public void deleteRisk(Risk risk) {
        rt.deleteRisk(risk);
    }


    private void DBFacade() throws SQLException {

        String password = User.getText();
        String username = Pass.getText();
        Connection connection = DriverManager.getConnection
                ("jdbc:mysql://localhost/world?serverTimezone=UTC", username, password);
        System.out.println("Database connected.");

        // Create a statement
        stmt = connection.createStatement();

    }
    private void CheckButton() {

    }
}

