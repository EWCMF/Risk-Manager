package ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import logic.RiskManagerController;
import persistence.DBFacade;

import java.io.IOException;

public class MainWindowController {
    private static boolean initialized;
    public static int countRisks;

    @FXML private TableView<RiskUI> riskTable;


    @FXML
    public void initialize() {
        showRisks();
        if (!initialized) {
        for (int i = 0; i < riskTable.getItems().size(); i++) {
            RiskManagerController.initialRisks(
                    riskTable.getItems().get(i).description,
                    riskTable.getItems().get(i).probability,
                    riskTable.getItems().get(i).consequence);
            }
            initialized = true;
        }
    }

    @FXML
    private void createRiskWindow() throws IOException {

        Parent cRisk = FXMLLoader.load(getClass().getResource("CreateRiskWindow.fxml"));
        Scene scene = new Scene(cRisk);
        Stage appStage = new Stage();
        appStage.setScene(scene);
        appStage.setTitle("Create Risk");
        appStage.initOwner(Main.window);
        appStage.initModality(Modality.WINDOW_MODAL);
        appStage.show();
        appStage.setOnCloseRequest(we -> RiskManagerController.deleteLastAddedRisk());
        logic.RiskManagerController.createRisk();
    }

    @FXML
    private void editRiskWindow() throws IOException {
        if (riskTable.getSelectionModel().getSelectedItem() != null) {
            EditRiskWindowController.currentlyEditedDescription = riskTable.getSelectionModel().getSelectedItem().description;
            EditRiskWindowController.newConsequence = riskTable.getSelectionModel().getSelectedItem().consequence;
            EditRiskWindowController.newProbability = riskTable.getSelectionModel().getSelectedItem().probability;
            EditRiskWindowController.newDescription = riskTable.getSelectionModel().getSelectedItem().description;

            Parent cRisk = FXMLLoader.load(getClass().getResource("editRiskWindow.fxml"));
            Scene scene = new Scene(cRisk);
            Stage appStage = new Stage();
            appStage.setScene(scene);
            appStage.setTitle("Edit Risk");
            appStage.initOwner(Main.window);
            appStage.initModality(Modality.WINDOW_MODAL);
            appStage.show();
        }
    }

    public void showRisks() {
            DBFacade dbFacade = new DBFacade();
            ObservableList<RiskUI> risks = dbFacade.getRisksList();

            riskTable.setItems(risks);
    }

    public void changeToStrategyWindow() throws IOException {
        Parent root = FXMLLoader.load(MainWindowController.class.getResource("StrategyWindow.fxml"));
        ui.Main.window.setTitle("Risk Manager - Strategies");
        ui.Main.window.setScene(new Scene(root, 720, 510));
        ui.Main.window.show();
    }

    @FXML
    public void deleteSelectedRisk() {
        if (riskTable.getSelectionModel().getSelectedItem() != null) {
            DBFacade dbFacade = new DBFacade();
            dbFacade.deleteRisk(riskTable.getSelectionModel().getSelectedItem().description);
            showRisks();

        }
    }
}
