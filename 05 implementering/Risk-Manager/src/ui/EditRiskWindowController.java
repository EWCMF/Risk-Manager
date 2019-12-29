package ui;

import domain.Risk;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import logic.RiskManagerController;
import persistence.DBFacade;

import java.io.IOException;
import java.util.InputMismatchException;

public class EditRiskWindowController {
    static int currentlyEditedID;
    static String newDescription;
    static Double newProbability;
    static Double newConsequence;

    @FXML
    private Button createRiskButton;

    @FXML private javafx.scene.control.TextArea riskDescription;
    @FXML private javafx.scene.control.TextArea riskProbability;
    @FXML private javafx.scene.control.TextArea riskConsequence;

    public void initialize() {
        riskDescription.setText(newDescription);
        riskProbability.setText(newProbability.toString());
        riskConsequence.setText(newConsequence.toString());
    }

    @FXML
    private void specifyRiskWindow(ActionEvent event) {
        String d = riskDescription.getText();
        Double p = Double.parseDouble(riskProbability.getText());
        Double c = Double.parseDouble(riskConsequence.getText());

        try {
            Stage stage = (Stage) createRiskButton.getScene().getWindow();
            Risk risk = RiskManagerController.selectRiskByID(currentlyEditedID);
            RiskManagerController.specifyRisk(risk, d, p, c);
            RiskManagerController.updateRiskDB(risk);
            stage.close();
        } catch (NumberFormatException e) {
            System.out.println("Wrong input");
        }
    }

    @FXML
    private void linkStrategyWindow() throws IOException {
        Parent cRisk = FXMLLoader.load(getClass().getResource("LinkStrategyWindow.fxml"));
        Scene scene = new Scene(cRisk);
            Stage appStage = new Stage();
            appStage.setScene(scene);
            appStage.setTitle("Link Strategy");
            appStage.initOwner(Main.window);
            appStage.initModality(Modality.WINDOW_MODAL);
            appStage.show();
    }
}
