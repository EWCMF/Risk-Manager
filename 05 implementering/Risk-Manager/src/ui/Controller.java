package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import logic.RiskManagerController;
import persistence.DBFacade;

import java.io.IOException;
import java.sql.*;

public class Controller {
    public static String user;
    public static String pass;

    @FXML
    private PasswordField User = new PasswordField();

    @FXML
    private PasswordField Pass = new PasswordField();

    @FXML
    private Button Confirm = new Button();


    @FXML private javafx.scene.control.TextArea riskDescription;
    @FXML private javafx.scene.control.TextArea riskProbability;
    @FXML private javafx.scene.control.TextArea riskConsequence;

    @FXML private TableView<RiskUI> riskTable;
    @FXML private Button createRiskButton;
    @FXML private Button updateButton;

    @FXML private TableColumn<RiskUI, String> descriptionColumn;
    @FXML private TableColumn<RiskUI, Double> probabilityColumn;
    @FXML private TableColumn<RiskUI, Double> consequenceColumn;
    @FXML private TableColumn<RiskUI, Double> exposureColumn;
    @FXML private TableColumn<RiskUI, Boolean> strategyColumn;


    @FXML
    private void saveUser() throws SQLException, IOException {
        user = User.getText();
        pass = Pass.getText();

        DBFacade.initializeDB();

        if (DBFacade.conneced) {
            changeToMainWindow();
        }
    }

    @FXML
    private void createRiskWindow(ActionEvent event) throws IOException {

        Parent cRisk = FXMLLoader.load(getClass().getResource("Create_Risk_Window.fxml"));
        Scene scene = new Scene(cRisk);
        Stage appStage = new Stage();
        appStage.setScene(scene);
        appStage.setTitle("Create Risk");
        appStage.show();
        appStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                logic.RiskManagerController.deleteLastAdded();
            }
        });
        logic.RiskManagerController.createRisk();
    }

    public void changeToMainWindow() throws IOException {
        Parent root = FXMLLoader.load(Controller.class.getResource("Main.fxml"));
        ui.Main.window.setTitle("Risk Manager");
        ui.Main.window.setScene(new Scene(root, 720, 510));
        ui.Main.window.show();
    }

    @FXML
    private void specifyRiskWindow(ActionEvent event) {
        try {
            RiskManagerController.specifyRisk(
                    RiskManagerController.getLastAdded(),
                    riskDescription.getText(),
                    Double.parseDouble(riskProbability.getText()),
                    Double.parseDouble(riskConsequence.getText()));
            Stage stage = (Stage) createRiskButton.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            System.out.println("Wrong input");
        }
    }

    public Connection getConnection() {
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/risk-manager?serverTimezone=UTC","root","Fredrik10");
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
