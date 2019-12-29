package persistence;

import domain.Risk;
import domain.Strategy;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;


public class DBFacade {

    public static boolean conneced = false;
    public static Statement stmt;
    private static String validUsername;
    private static String validPassword;

    public static void initializeDB(String username, String password) {
        try {
        Connection connection = DriverManager.getConnection
                ("jdbc:mysql://localhost/risk-managerf?serverTimezone=UTC", username, password);
        System.out.println("Database connected.");

        conneced = true;
        validUsername = username;
        validPassword = password;
        // Create a statement
        stmt = connection.createStatement();
        } catch (Exception e) {
            System.out.println("Kan ikke logge ind.");
        }

    }

    public Connection getConnection() {
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/risk-managerf?serverTimezone=UTC",validUsername,validPassword);
            return conn;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
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

    public void linkStrategy(Integer strategyID, int id) {
        String query = "UPDATE risk set strategy = '"+strategyID+"' where id = '"+id+"'";
        executeQuery(query);
    }

    public void removeLink(int id) {
        String query = "UPDATE risk set strategy = null where id = '"+id+"'";
        executeQuery(query);
    }

    public void updateRisk(String description, Double probability, Double consequence, Double exposure, int id) {
        String query = "UPDATE risk set description = '"+description+"', " +
                "probability = '"+probability+"', " +
                "consequence = '"+consequence+"', " +
                "exposure = '"+exposure+"' where id = '"+id+"'";
        executeQuery(query);
    }

    public void updateStrategy(String name, String category, String description, int id) {
        String query = "UPDATE strategy set name = '"+name+"', " +
                "category = '"+category+"', " +
                "description = '"+description+"' where id = '"+id+"'";
        executeQuery(query);
    }

    public void deleteRisk(int id) {
        String query = "DELETE FROM risk WHERE id like '"+id+"'";
        executeQuery(query);
    }

    public void deleteStrategy(int id) {
        String query = "DELETE FROM strategy WHERE id like '"+id+"'";
        executeQuery(query);
    }

    public void insertRisk(Integer id, String description, Double probability, Double consequence, Double exposure) {
        String query = "insert into risk (id, description, probability, consequence, exposure) values ('"+id+"','"+description+"','"+probability+"','"+consequence+"','"+exposure+"')";

        executeQuery(query);
    }

    public void insertStrategy(int id, String name, String category, String description) {
        String query = "insert into strategy (id, name, category, description) values ('"+id+"','"+name+"','"+category+"','"+description+"')";

        executeQuery(query);
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
            Strategy strategy = null;

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
                strategies = new Strategy(rs.getInt("id"),rs.getString("name"),rs.getString("category"),rs.getString("description"));
                strategyList.add(strategies);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strategyList;
    }

    public Strategy getLinkedStrategy(int strategyID) throws SQLException {
        Connection connection = getConnection();
        String query = "SELECT * FROM strategy where id ="+strategyID;
        Statement st;
        ResultSet rs;
        Strategy strategy = null;

        st = connection.createStatement();
        rs = st.executeQuery(query);
        while (rs.next()) {
            strategy = new Strategy(rs.getInt("id"), rs.getString("name"), rs.getString("category"), rs.getString("description"));
        }
        return strategy;
    }
}
