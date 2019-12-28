package ui;

import domain.Risk;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.stage.Modality;
import javafx.stage.Stage;
import logic.RiskManagerController;
import persistence.DBFacade;

import java.io.IOException;

public class MainWindowController {
    private static boolean initialized;
    public static int numRisks;

    @FXML private TableView<Risk> riskTable;
    @FXML private TextArea strategyArea;

    @FXML
    public void initialize() {
        showRisks();
        if (!initialized) {
        for (int i = 0; i < riskTable.getItems().size(); i++) {
            RiskManagerController.initialRisks(
                    riskTable.getItems().get(i).getDescription(),
                    riskTable.getItems().get(i).getProbability(),
                    riskTable.getItems().get(i).getConsequence());
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
            EditRiskWindowController.currentlyEditedDescription = riskTable.getSelectionModel().getSelectedItem().getDescription();
            EditRiskWindowController.newConsequence = riskTable.getSelectionModel().getSelectedItem().getConsequence();
            EditRiskWindowController.newProbability = riskTable.getSelectionModel().getSelectedItem().getProbability();
            EditRiskWindowController.newDescription = riskTable.getSelectionModel().getSelectedItem().getDescription();

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
            ObservableList<Risk> risks = dbFacade.getRisksList();

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
            dbFacade.deleteRisk(riskTable.getSelectionModel().getSelectedItem().getDescription());
            showRisks();
            strategyArea.setText("");
        }
    }

    public void showSelectedRiskStrategy() {
        if (riskTable.getSelectionModel().getSelectedItem() != null) {
            DBFacade dbFacade = new DBFacade();
            strategyArea.setText(dbFacade.getSelectedRiskStrategy(riskTable.getSelectionModel().getSelectedItem().getStrategy().getId()));
        }
    }
}
