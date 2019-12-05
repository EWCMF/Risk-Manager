package logic;

import ui.MainWindowController;

public class RiskManagerController {
    static RiskTable rt = new RiskTable();
    static StrategyTable st = new StrategyTable();

    public static void createRisk() {
        rt.createRisk();
    }

    public static void specifyRisk(Risk risk, String description, double probability, double consequence) {
        rt.specifyRisk(risk, description, probability, consequence);
    }

    public static void createStrategy() {
        st.createStrategy();
    }

    public static void specifyStrategy(Strategy strategy, String description, String category) {
        st.specifyRisk(strategy, description, category);
    }

    public void linkStrategy(Risk risk, Strategy strategy) {
        rt.linkStrategy(risk, strategy);
    }

    public static void deleteRisk(Risk risk) {
        rt.deleteRisk(risk);
    }

    public static void deleteLastAddedRisk() {
        rt.deleteLastAdded();
    }

    public static void deleteLastAddedStrategy() {
        st.deleteLastAdded();
    }

    public static Risk getLastAddedRisk() {
        return rt.risks.get(rt.risks.size() - 1);
    }

    public static Strategy getLastAddedStrategy() {
        return st.strategies.get(rt.risks.size() - 1);
    }

    public static void addRiskToDB(Risk risk) {
        String description = risk.description;
        Double probability = risk.probability;
        Double consequence = risk.consequence;
        Double exposure = risk.exposure;
        Boolean strategy = false;

        MainWindowController mainWindowController = new MainWindowController();
        mainWindowController.insertRisk(description, probability, consequence, exposure, strategy);
    }

    public static void addStrategyToDB(Strategy strategy) {
        String description = strategy.description;
        String category = strategy.category;

        MainWindowController mainWindowController = new MainWindowController();

    }




}

