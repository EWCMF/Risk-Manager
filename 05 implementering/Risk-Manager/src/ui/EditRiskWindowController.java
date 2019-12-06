package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import persistence.DBFacade;

import java.io.IOException;
import java.util.InputMismatchException;

public class EditRiskWindowController {
    static String currentlyEditedDescription;
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
            DBFacade dbFacade = new DBFacade();
            dbFacade.updateRisk(d, p, c, p * c / 100, currentlyEditedDescription);
            stage.close();
        } catch (InputMismatchException e) {
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
