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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import logic.RiskManagerController;

import java.io.IOException;

public class Controller {

    @FXML private javafx.scene.control.TextArea riskDescription;
    @FXML private javafx.scene.control.TextArea riskProbability;
    @FXML private javafx.scene.control.TextArea riskConsequence;

    @FXML private TableView<RiskUI> riskTable;
    @FXML private Button createRiskButton;

    @FXML private TableColumn<RiskUI, String> descriptionColumn;
    @FXML private TableColumn<RiskUI, Double> probabilityColumn;
    @FXML private TableColumn<RiskUI, Double> consequenceColumn;
    @FXML private TableColumn<RiskUI, Double> exposureColumn;
    @FXML private TableColumn<RiskUI, Boolean> strategyColumn;

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

    @FXML
    private void specifyRiskWindow(ActionEvent event) throws Exception {
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


    private ObservableList<RiskUI> getRisks() {
        ObservableList<RiskUI> risks = FXCollections.observableArrayList();
        //SQL
        return risks;
    }

    public void UpdateTable() {
        ObservableList<RiskUI> risks = FXCollections.observableArrayList();

        riskTable.setItems(risks);
    }
}
