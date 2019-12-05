package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import logic.RiskManagerController;

import java.util.InputMismatchException;

public class CreateRiskWindowController {

    @FXML private Button createRiskButton;

    @FXML private javafx.scene.control.TextArea riskDescription;
    @FXML private javafx.scene.control.TextArea riskProbability;
    @FXML private javafx.scene.control.TextArea riskConsequence;

    @FXML
    private void specifyRiskWindow(ActionEvent event) {
        try {
            RiskManagerController.specifyRisk(
                    RiskManagerController.getLastAddedRisk(),
                    riskDescription.getText(),
                    Double.parseDouble(riskProbability.getText()),
                    Double.parseDouble(riskConsequence.getText()));
            Stage stage = (Stage) createRiskButton.getScene().getWindow();
            stage.close();
        } catch (InputMismatchException e) {
            System.out.println("Wrong input");
        }
    }
}
