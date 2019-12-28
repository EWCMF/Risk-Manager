package ui;

import domain.Strategy;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import logic.RiskManagerController;
import persistence.DBFacade;

import java.util.InputMismatchException;

public class LinkStrategyWindowController {

    @FXML
    private Button linkStrategyButton;

    @FXML
    private TableView<Strategy> strategyTable;
    @FXML private TextArea description;

    public void initialize() {
        showStrategies();
    }

    @FXML
    private void LinkStrategyWindow(ActionEvent event) {
        if (strategyTable.getSelectionModel().getSelectedItem() != null) {
            try {
                Stage stage = (Stage) linkStrategyButton.getScene().getWindow();
                DBFacade dbFacade = new DBFacade();
                dbFacade.linkStrategy(strategyTable.getSelectionModel().getSelectedItem().getId(), EditRiskWindowController.currentlyEditedID);
                stage.close();
            } catch (InputMismatchException e) {
                System.out.println("Wrong input");
            }
        }
    }



    public void showStrategies() {
        RiskManagerController riskManagerController = new RiskManagerController();
        ObservableList<Strategy> strategies = riskManagerController.getStrategyTable();

        strategyTable.setItems(strategies);
    }

    @FXML
    public void showDescription() {
        if (strategyTable.getSelectionModel().getSelectedItem() != null) {
            description.setText(strategyTable.getSelectionModel().getSelectedItem().getDescription());
        }
    }
}
