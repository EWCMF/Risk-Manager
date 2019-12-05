package persistence;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ui.LoginWindowController;
import ui.RiskUI;

import java.sql.*;


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

    public void insertRisk(String description, Double probability, Double consequence, Double exposure, Boolean strategy) {
        String query = "insert into risk (description, probability, consequence, exposure) values ('"+description+"','"+probability+"','"+consequence+"','"+exposure+"')";

        executeQuery(query);
    }

    public void insertStrategy(String description, String category) {
        String query = "insert into strategy (description, category) values ('"+description+"','"+category+"')";

        executeQuery(query);
    }

    public void executeQuery(String query) {
        Connection conn = getConnection();
        Statement st;
        try {
            st = conn.createStatement();
            st.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/risk-manager?serverTimezone=UTC",LoginWindowController.user,LoginWindowController.pass);
            return conn;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public ObservableList<RiskUI> getRisksList(){
        ObservableList<RiskUI> riskList = FXCollections.observableArrayList();
        Connection connection = getConnection();
        String query = "SELECT * FROM risk";
        Statement st;
        ResultSet rs;

        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            RiskUI risks;
            while(rs.next()) {
                risks = new RiskUI(rs.getString("description"),rs.getDouble("probability"),rs.getDouble("consequence"),rs.getDouble("exposure"), false);
                riskList.add(risks);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return riskList;
    }
}
