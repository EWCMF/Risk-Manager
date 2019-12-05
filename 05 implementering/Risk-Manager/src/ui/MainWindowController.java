package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import logic.RiskManagerController;

import java.io.IOException;
import java.sql.*;

public class MainWindowController {

    @FXML private TableView<RiskUI> riskTable;

    @FXML private TableColumn<RiskUI, String> descriptionColumn;
    @FXML private TableColumn<RiskUI, Double> probabilityColumn;
    @FXML private TableColumn<RiskUI, Double> consequenceColumn;
    @FXML private TableColumn<RiskUI, Double> exposureColumn;
    @FXML private TableColumn<RiskUI, Boolean> strategyColumn;

    @FXML
    public void initialize() {
        showRisks();
    }

    @FXML
    private void createRiskWindow(ActionEvent event) throws IOException {

        Parent cRisk = FXMLLoader.load(getClass().getResource("CreateRiskWindow.fxml"));
        Scene scene = new Scene(cRisk);
        Stage appStage = new Stage();
        appStage.setScene(scene);
        appStage.setTitle("Create Risk");
        appStage.initOwner(Main.window);
        appStage.initModality(Modality.WINDOW_MODAL);
        appStage.show();
        appStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                RiskManagerController.deleteLastAddedRisk();
            }
        });
        logic.RiskManagerController.createRisk();
    }

    @FXML
    private void createStrategyWindow(ActionEvent event) throws IOException {

        Parent cRisk = FXMLLoader.load(getClass().getResource("CreateStrategyWindow.fxml"));
        Scene scene = new Scene(cRisk);
        Stage appStage = new Stage();
        appStage.setScene(scene);
        appStage.setTitle("Create Strategy");
        appStage.initOwner(Main.window);
        appStage.initModality(Modality.WINDOW_MODAL);
        appStage.show();
        appStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                RiskManagerController.deleteLastAddedStrategy();
            }
        });
        logic.RiskManagerController.createStrategy();
    }

    public void insertRisk(String description, Double probability, Double consequence, Double exposure, Boolean strategy) {
        String query = "insert into risk (description, probability, consequence, exposure) values ('"+description+"','"+probability+"','"+consequence+"','"+exposure+"')";

        executeQuery(query);
        showRisks();
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

    public void showRisks() {
            ObservableList<RiskUI> risks = getRisksList();

            riskTable.setItems(risks);
    }
}
