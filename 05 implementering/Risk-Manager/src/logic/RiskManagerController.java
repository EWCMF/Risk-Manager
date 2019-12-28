package logic;

import domain.Risk;
import domain.Strategy;
import javafx.collections.ObservableList;
import persistence.DBFacade;
import ui.MainWindowController;
import ui.StrategyWindowController;

public class RiskManagerController {

    static RiskTable rt = new RiskTable();
    static StrategyTable st = new StrategyTable();



    public void initialRisks() {
                DBFacade dbFacade = new DBFacade();
                rt.risks = dbFacade.getRisksList();
                if (rt.risks.size() != 0) {
                    rt.lastID = rt.risks.get(rt.risks.size() - 1).getId() + 1;
                }
    }

    public void initialStrategies() {
            DBFacade dbFacade = new DBFacade();
            st.strategies = dbFacade.getStrategyList();
    }

    public static void createRisk() {
        rt.createRisk();
    }

    public static void specifyRisk(Risk risk, String description, double probability, double consequence) {
        rt.specifyRisk(risk, description, probability, consequence);
    }

    public static void createStrategy() {
        st.createStrategy();
        StrategyWindowController.numStrategies++;
    }

    public static void specifyStrategy(int id, String name, String description, String category) {
        st.specifyStrategy(id, name, description, category);
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

    public static void deleteRiskByID(int id) {
        Risk risk = null;
        for (int i = 0; i < rt.risks.size(); i++) {
            if (rt.risks.get(i).getId() == id); {
                risk = rt.risks.get(i);
            }
        }
        assert risk != null;
        {
            rt.deleteRisk(risk);
            DBFacade dbFacade = new DBFacade();
            dbFacade.deleteRisk(id);
        }
    }

    public static Risk selectRiskByID(int id) {
        Risk risk = null;
        for (int i = 0; i < rt.risks.size(); i++) {
            if (rt.risks.get(i).getId() == id) {
                risk = rt.risks.get(i);
            }
        }
        return risk;
    }

    public static Strategy getLastAddedStrategy() {
        return st.strategies.get(st.strategies.size() - 1);
    }

    public static void addRiskToDB(Risk risk) {
        Integer id = risk.getId();
        String description = risk.getDescription();
        Double probability = risk.getProbability();
        Double consequence = risk.getConsequence();
        Double exposure = risk.getExposure();

        DBFacade dbFacade = new DBFacade();
        dbFacade.insertRisk(id, description, probability, consequence, exposure);
    }

    public static void addStrategyToDB(Strategy strategy) {
        String name = strategy.getName();
        String description = strategy.getDescription();
        String category = strategy.getCategory();

        DBFacade dbFacade = new DBFacade();
        dbFacade.insertStrategy(name, description, category);

    }

    public ObservableList<Risk> getRiskTable() {
        return rt.risks;
    }

    public ObservableList<Strategy> getStrategyTable() {
        return st.strategies;
    }

    public ObservableList<Risk> getRisksDB() {
        DBFacade dbFacade = new DBFacade();
        return dbFacade.getRisksList();
    }

    public ObservableList<Strategy> getStrategiesDB() {
        DBFacade dbFacade = new DBFacade();

        return dbFacade.getStrategyList();
    }
}

