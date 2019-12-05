package logic;

import persistence.DBFacade;
import ui.MainWindowController;

public class RiskManagerController {

    static RiskTable rt = new RiskTable();
    static StrategyTable st = new StrategyTable();

    public static void initialRisks(String description, double probability, double consequence) {
                createRisk();
                specifyRisk(getLastAddedRisk(), description, probability, consequence);
    }

    public static void initialStrategies(String name, String description, String category) {
            createStrategy();
            specifyStrategy(getLastAddedStrategy(), name, description, category);

    }

    public static void createRisk() {
        rt.createRisk();
        MainWindowController.countRisks++;
    }

    public static void specifyRisk(Risk risk, String description, double probability, double consequence) {
        rt.specifyRisk(risk, description, probability, consequence);
    }

    public static void createStrategy() {
        st.createStrategy();
    }

    public static void specifyStrategy(Strategy strategy, String name, String description, String category) {
        st.specifyRisk(strategy, name, description, category);
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

    public static Risk getCurrentRisk(int selected) {
        return rt.risks.get(selected);
    }

    public static Strategy getLastAddedStrategy() {
        return st.strategies.get(st.strategies.size() - 1);
    }

    public static void addRiskToDB(Risk risk) {
        String description = risk.description;
        Double probability = risk.probability;
        Double consequence = risk.consequence;
        Double exposure = risk.exposure;
        Boolean strategy = false;

        DBFacade dbFacade = new DBFacade();
        dbFacade.insertRisk(description, probability, consequence, exposure, strategy);
    }

    public static void addStrategyToDB(Strategy strategy) {
        String name = strategy.name;
        String description = strategy.description;
        String category = strategy.category;

        DBFacade dbFacade = new DBFacade();
        dbFacade.insertStrategy(name, description, category);

    }




}

