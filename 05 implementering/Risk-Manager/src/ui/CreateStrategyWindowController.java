package ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import logic.RiskManagerController;

import java.util.InputMismatchException;

public class CreateStrategyWindowController {

    @FXML private Button createStrategyButton;

    @FXML private javafx.scene.control.TextArea strategyDescription;
    @FXML private javafx.scene.control.TextField strategyCategory;

    @FXML
    private void specifyStrategy() {
            RiskManagerController.specifyStrategy(
                    RiskManagerController.getLastAddedStrategy(),
                    strategyDescription.getText(),
                    strategyCategory.getText());
            Stage stage = (Stage) createStrategyButton.getScene().getWindow();
            stage.close();
    }

}
