package ui;

import domain.Strategy;
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

public class StrategyWindowController {

    @FXML private TableView<Strategy> strategyTable;
    @FXML private TextArea description;

    public void initialize() {
        showStrategies();
    }

    @FXML
    private void createStrategyWindow() throws IOException {

        Parent cRisk = FXMLLoader.load(getClass().getResource("CreateStrategyWindow.fxml"));
        Scene scene = new Scene(cRisk);
        Stage appStage = new Stage();
        appStage.setScene(scene);
        appStage.setTitle("Create Strategy");
        appStage.initOwner(Main.window);
        appStage.initModality(Modality.WINDOW_MODAL);
        appStage.show();
        appStage.setOnCloseRequest(we -> RiskManagerController.deleteLastAddedStrategy());
        logic.RiskManagerController.createStrategy();
    }

    @FXML
    private void editStrategyWindow() throws IOException {
        if (strategyTable.getSelectionModel().getSelectedItem() != null) {
            EditStrategyWindowController.currentlyEditedID = strategyTable.getSelectionModel().getSelectedItem().getId();
            EditStrategyWindowController.newName = strategyTable.getSelectionModel().getSelectedItem().getName();
            EditStrategyWindowController.newCategory = strategyTable.getSelectionModel().getSelectedItem().getCategory();
            EditStrategyWindowController.newDescription = strategyTable.getSelectionModel().getSelectedItem().getDescription();

            description.setText("");
            Parent cRisk = FXMLLoader.load(getClass().getResource("EditStrategyWindow.fxml"));
            Scene scene = new Scene(cRisk);
            Stage appStage = new Stage();
            appStage.setScene(scene);
            appStage.setTitle("Edit Strategy");
            appStage.initOwner(Main.window);
            appStage.initModality(Modality.WINDOW_MODAL);
            appStage.show();
            appStage.setOnCloseRequest(we -> RiskManagerController.deleteLastAddedStrategy());
            logic.RiskManagerController.createStrategy();
        }
    }



    public void showStrategies() {
        RiskManagerController riskManagerController = new RiskManagerController();
        ObservableList<Strategy> strategies = riskManagerController.getStrategyTable();

        strategyTable.setItems(strategies);
        strategyTable.refresh();
    }

    @FXML
    public void changeToMainWindow() throws IOException {
        Parent root = FXMLLoader.load(MainWindowController.class.getResource("MainWindow.fxml"));
        ui.Main.window.setTitle("Risk Manager - Risks");
        ui.Main.window.setScene(new Scene(root, 720, 510));
        ui.Main.window.show();
    }

    @FXML
    public void showDescription() {
        if (strategyTable.getSelectionModel().getSelectedItem() != null) {
            description.setText(strategyTable.getSelectionModel().getSelectedItem().getDescription());
        }
    }

    @FXML
    public void deleteSelectedStrategy() {
        if (strategyTable.getSelectionModel().getSelectedItem() != null) {
            RiskManagerController.deleteStrategyByID(strategyTable.getSelectionModel().getSelectedItem().getId());
            showStrategies();
            description.setText("");
        }
    }
}
