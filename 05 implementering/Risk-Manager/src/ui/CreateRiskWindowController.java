package ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import logic.RiskManagerController;

import java.io.IOException;

public class CreateRiskWindowController {

    @FXML private Button createRiskButton;

    @FXML private javafx.scene.control.TextArea riskDescription;
    @FXML private javafx.scene.control.TextArea riskProbability;
    @FXML private javafx.scene.control.TextArea riskConsequence;

    @FXML
    private void specifyRiskWindow() throws IOException {
        try {
            RiskManagerController.specifyRisk(
                    RiskManagerController.getLastAddedRisk(),
                    riskDescription.getText(),
                    Double.parseDouble(riskProbability.getText()),
                    Double.parseDouble(riskConsequence.getText()));
            Stage stage = (Stage) createRiskButton.getScene().getWindow();
            RiskManagerController.addRiskToDB(RiskManagerController.getLastAddedRisk());
            stage.close();
        } catch (NumberFormatException e) {
            ErrorWindowController error = new ErrorWindowController();
            error.owner = (Stage) createRiskButton.getScene().getWindow();
            error.newError("Sandsynlighed og konsekvens skal være mellem 0 og 100.");
        }
    }
}
