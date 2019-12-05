package persistence;

import ui.LoginWindowController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class DBFacade {

    public static boolean conneced = false;
    public static Statement stmt;

    public static void initializeDB() throws SQLException {

        String password = LoginWindowController.pass;
        String username = LoginWindowController.user;

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
}
