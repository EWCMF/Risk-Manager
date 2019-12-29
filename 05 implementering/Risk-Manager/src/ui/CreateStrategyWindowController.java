package ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import logic.RiskManagerController;

public class CreateStrategyWindowController {

    @FXML private Button createStrategyButton;

    @FXML private javafx.scene.control.TextField strategyName;
    @FXML private javafx.scene.control.TextArea strategyDescription;
    @FXML private javafx.scene.control.TextField strategyCategory;

    @FXML
    private void specifyStrategy() {
            RiskManagerController.specifyStrategy(RiskManagerController.getLastAddedStrategy(),
                    strategyName.getText(),
                    strategyCategory.getText(),
                    strategyDescription.getText());
            RiskManagerController.addStrategyToDB(RiskManagerController.getLastAddedStrategy());
            Stage stage = (Stage) createStrategyButton.getScene().getWindow();
            stage.close();
    }

}
