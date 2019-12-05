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
import persistence.DBFacade;

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



    public void showRisks() {
            DBFacade dbFacade = new DBFacade();
            ObservableList<RiskUI> risks = dbFacade.getRisksList();

            riskTable.setItems(risks);
    }
}
