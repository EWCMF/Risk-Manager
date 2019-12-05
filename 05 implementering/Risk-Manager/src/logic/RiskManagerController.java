package logic;

import ui.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class RiskManagerController {
    public static RiskTable rt = new RiskTable();
    StrategyTable st = new StrategyTable();

    public static void createRisk() {
        rt.createRisk();
    }

    public static void specifyRisk(Risk risk, String description, double probability, double consequence) {
        rt.specifyRisk(risk, description, probability, consequence);
    }

    public void linkStrategy(Risk risk, Strategy strategy) {
        rt.linkStrategy(risk, strategy);
    }

    public static void deleteRisk(Risk risk) {
        rt.deleteRisk(risk);
    }

    public static void deleteLastAdded() {
        rt.deleteLastAdded();
    }

    public static Risk getLastAdded() {
        return rt.risks.get(rt.risks.size() - 1);
    }

    public static void addToDB(Risk risk) {
        String description = risk.description;
        Double probability = risk.probability;
        Double consequence = risk.consequence;
        Double exposure = risk.exposure;
        Boolean strategy = false;

        Controller controller = new Controller();
        controller.insertRisk(description, probability, consequence, exposure, strategy);
    }




}

