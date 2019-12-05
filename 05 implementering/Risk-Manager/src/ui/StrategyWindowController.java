package ui;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import logic.RiskManagerController;
import persistence.DBFacade;

import java.io.IOException;

public class StrategyWindowController {
    private static boolean initialized;

    @FXML private TableView<StrategyUI> strategyTable;
    @FXML private TextArea description;

    public void initialize() {
        showStrategies();
        if (!initialized) {
            for (int i = 0; i < strategyTable.getItems().size(); i++) {
                RiskManagerController.initialStrategies(
                        strategyTable.getItems().get(i).name,
                        strategyTable.getItems().get(i).description,
                        strategyTable.getItems().get(i).category);
            }
            initialized = true;
        }
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



    public void showStrategies() {
        DBFacade dbFacade = new DBFacade();
        ObservableList<StrategyUI> risks = dbFacade.getStrategyList();

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
        description.setText(strategyTable.getSelectionModel().getSelectedItem().description);
    }

    @FXML
    public void deleteSelectedStrategy() {
        DBFacade dbFacade = new DBFacade();
        dbFacade.deleteStrategy(strategyTable.getSelectionModel().getSelectedItem().name);
        showStrategies();
    }
}
