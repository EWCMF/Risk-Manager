package ui;

import domain.Strategy;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import logic.RiskManagerController;
import persistence.DBFacade;

import java.util.InputMismatchException;

public class EditStrategyWindowController {
    static int currentlyEditedID;
    static String newName;
    static String newCategory;
    static String newDescription;

    @FXML
    private Button createStrategyButton;

    @FXML private javafx.scene.control.TextField strategyName;
    @FXML private javafx.scene.control.TextArea strategyDescription;
    @FXML private javafx.scene.control.TextField strategyCategory;

    public void initialize() {
        strategyName.setText(newName);
        strategyCategory.setText(newCategory);
        strategyDescription.setText(newDescription);
    }

    @FXML
    private void specifyStrategyWindow(ActionEvent event) {
        String n = strategyName.getText();
        String c = strategyCategory.getText();
        String d = strategyDescription.getText();

        try {
            Stage stage = (Stage) createStrategyButton.getScene().getWindow();
            Strategy strategy = RiskManagerController.selectStrategyByID(currentlyEditedID);
            RiskManagerController.specifyStrategy(strategy, n, c, d);
            RiskManagerController.updateStrategyDB(strategy);
            stage.close();
        } catch (InputMismatchException e) {
            System.out.println("Wrong input");
        }
    }
}
