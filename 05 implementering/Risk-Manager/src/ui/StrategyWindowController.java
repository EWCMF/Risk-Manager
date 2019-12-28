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
    static boolean initialized;
    public static int numStrategies;

    @FXML private TableView<Strategy> strategyTable;
    @FXML private TextArea description;

    public void initialize() {
        showStrategies();
        if (!initialized) {
            for (int i = 0; i < strategyTable.getItems().size(); i++) {
                RiskManagerController.initialStrategies(0,
                        strategyTable.getItems().get(i).getName(),
                        strategyTable.getItems().get(i).getDescription(),
                        strategyTable.getItems().get(i).getCategory());
            }
            initialized = true;
        }
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
            EditStrategyWindowController.currentlyEditedName = strategyTable.getSelectionModel().getSelectedItem().getName();
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
        DBFacade dbFacade = new DBFacade();
        ObservableList<Strategy> risks = dbFacade.getStrategyList();

        strategyTable.setItems(risks);
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
            DBFacade dbFacade = new DBFacade();
            dbFacade.deleteStrategy(strategyTable.getSelectionModel().getSelectedItem().getName());
            showStrategies();
            description.setText("");
        }
    }
}
