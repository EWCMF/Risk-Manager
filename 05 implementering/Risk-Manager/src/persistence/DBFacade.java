package persistence;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static ui.Controller.pass;
import static ui.Controller.user;

public class DBFacade {


    public static boolean conneced = false;




    public static Statement stmt;




    public static void initializeDB() throws SQLException {

        String password = pass;
        String username = user;

        try {
        Connection connection = DriverManager.getConnection
                ("jdbc:mysql://localhost/risk-manager?serverTimezone=UTC", username, password);
        System.out.println("Database connected.");

        conneced = true;
        // Create a statement
        stmt = connection.createStatement();
        } catch (Exception e) {
            System.out.println("Kan ikke logge ind.");
        }

    }

    private void CheckButton() {

    }
}
