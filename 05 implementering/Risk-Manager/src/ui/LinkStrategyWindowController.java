package ui;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.stage.Modality;
import javafx.stage.Stage;
import logic.RiskManagerController;
import persistence.DBFacade;

import java.util.InputMismatchException;

public class LinkStrategyWindowController {

    @FXML
    private Button linkStrategyButton;

    @FXML
    private TableView<StrategyUI> strategyTable;
    @FXML private TextArea description;

    public void initialize() {
        showStrategies();
        if (!StrategyWindowController.initialized) {
            for (int i = 0; i < strategyTable.getItems().size(); i++) {
                RiskManagerController.initialStrategies(
                        strategyTable.getItems().get(i).name,
                        strategyTable.getItems().get(i).description,
                        strategyTable.getItems().get(i).category);
            }
            StrategyWindowController.initialized = true;
        }
    }

    @FXML
    private void LinkStrategyWindow(ActionEvent event) {
        if (strategyTable.getSelectionModel().getSelectedItem() != null) {
            try {
                Stage stage = (Stage) linkStrategyButton.getScene().getWindow();
                DBFacade dbFacade = new DBFacade();
                dbFacade.linkStrategy(strategyTable.getSelectionModel().getSelectedItem().id, EditRiskWindowController.currentlyEditedDescription);
                stage.close();
            } catch (InputMismatchException e) {
                System.out.println("Wrong input");
            }
        }
    }



    public void showStrategies() {
        DBFacade dbFacade = new DBFacade();
        ObservableList<StrategyUI> risks = dbFacade.getStrategyList();

        strategyTable.setItems(risks);
    }

    @FXML
    public void showDescription() {
        if (strategyTable.getSelectionModel().getSelectedItem() != null) {
            description.setText(strategyTable.getSelectionModel().getSelectedItem().description);
        }
    }
}
