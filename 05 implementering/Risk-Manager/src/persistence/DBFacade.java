package persistence;

import domain.Risk;
import domain.Strategy;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ui.*;

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

    public void linkStrategy(Integer strategyID, int id) {
        String query = "UPDATE risk set strategy = '"+strategyID+"' where id = '"+id+"'";
        executeQuery(query);
    }

    public void updateRisk(String description, Double probability, Double consequence, Double exposure, int id) {
        String query = "UPDATE risk set description = '"+description+"', " +
                "probability = '"+probability+"', " +
                "consequence = '"+consequence+"', " +
                "exposure = '"+exposure+"' where id = '"+id+"'";
        executeQuery(query);
    }

    public void updateStrategy(String name, String category, String description, String last) {
        String query = "UPDATE strategy set name = '"+name+"', " +
                "category = '"+category+"', " +
                "description = '"+description+"' where name = '"+last+"'";
        executeQuery(query);
    }

    public void deleteRisk(String selected) {
        String query = "DELETE FROM risk WHERE description like '"+selected+"'";
        executeQuery(query);
    }

    public void deleteStrategy(String selected) {
        String query = "DELETE FROM strategy WHERE name like '"+selected+"'";
        executeQuery(query);
    }

    public void insertRisk(String description, Double probability, Double consequence, Double exposure) {
        String query = "insert into risk (description, probability, consequence, exposure) values ('"+description+"','"+probability+"','"+consequence+"','"+exposure+"')";

        executeQuery(query);
    }

    public void insertStrategy(String name, String description, String category) {
        String query = "insert into strategy (name, description, category, strategyID) values ('"+name+"','"+description+"','"+category+"','"+ StrategyWindowController.numStrategies+"')";

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

    public ObservableList<Risk> getRisksList(){
        ObservableList<Risk> riskList = FXCollections.observableArrayList();
        Connection connection = getConnection();
        String query = "SELECT * FROM risk";
        Statement st;
        ResultSet rs;

        try {
            boolean hasStrategy;
            st = connection.createStatement();
            rs = st.executeQuery(query);
            Risk risks;
            Strategy strategy = new Strategy();

            while(rs.next()) {
                if (rs.getInt("strategy") > 0) {
                    strategy = getLinkedStrategy(rs.getInt("strategy"));
                    hasStrategy = true;
                }
                else {
                    hasStrategy = false;
                }
                risks = new Risk(rs.getInt("id"),rs.getString("description"),rs.getDouble("probability"),rs.getDouble("consequence"),rs.getDouble("exposure"), strategy, hasStrategy);
                riskList.add(risks);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return riskList;
    }

    public ObservableList<Strategy> getStrategyList(){
        ObservableList<Strategy> strategyList = FXCollections.observableArrayList();
        Connection connection = getConnection();
        String query = "SELECT * FROM strategy";
        Statement st;
        ResultSet rs;

        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            Strategy strategies;
            while(rs.next()) {
                strategies = new Strategy(rs.getInt("strategyID"),rs.getString("name"),rs.getString("description"),rs.getString("category"));
                strategyList.add(strategies);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strategyList;
    }

    public String getSelectedRiskStrategy(Integer selected){
        Connection connection = getConnection();
        String query = "SELECT description FROM strategy where strategyID ="+selected;
        Statement st;
        ResultSet rs;
        String description = "";
        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            while(rs.next()) {

                description = rs.getString("description");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return description;
    }

    public Strategy getLinkedStrategy(int strategyID) throws SQLException {
        Connection connection = getConnection();
        String query = "SELECT * FROM strategy where strategyID ="+strategyID;
        Statement st;
        ResultSet rs;
        Strategy strategy = null;

        st = connection.createStatement();
        rs = st.executeQuery(query);
        while (rs.next()) {
            strategy = new Strategy(rs.getInt("StrategyID"), rs.getString("name"), rs.getString("category"), rs.getString("description"));
        }
        return strategy;
    }
}
