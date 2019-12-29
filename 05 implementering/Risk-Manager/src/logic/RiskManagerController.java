package logic;

import domain.Risk;
import domain.Strategy;
import javafx.collections.ObservableList;
import persistence.DBFacade;

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
            if (st.strategies.size() != 0) {
                st.lastID = st.strategies.get(st.strategies.size() - 1).getId() + 1;
            }
    }

    public static void createRisk() {
        rt.createRisk();
    }

    public static void createStrategy() {
        st.createStrategy();
    }

    public static void specifyRisk(Risk risk, String description, double probability, double consequence) {
        rt.specifyRisk(risk, description, probability, consequence);
    }

    public static void specifyStrategy(Strategy strategy, String name, String category, String description) {
        st.specifyStrategy(strategy, name, category, description);
    }

    public static void deleteLastAddedRisk() {
        rt.deleteLastAdded();
    }

    public static void deleteLastAddedStrategy() {
        st.deleteLastAdded();
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

    public static void deleteStrategyByID(int id) {
        Strategy strategy = null;
        for (int i = 0; i < st.strategies.size(); i++) {
            if (st.strategies.get(i).getId() == id) {
                strategy = st.strategies.get(i);
            }
        }
        assert strategy != null;
        {
            DBFacade dbFacade = new DBFacade();
            for (int i = 0; i < rt.risks.size(); i++) {
                if (rt.risks.get(i).getStrategy().getId() == strategy.getId()) {
                    rt.risks.get(i).setHasStrategy(false);
                    dbFacade.removeLink(rt.risks.get(i).getId());
                }
            }
            st.deleteStrategy(strategy);
            dbFacade.deleteStrategy(id);
        }
    }

    public static Risk getLastAddedRisk() {
        return rt.risks.get(rt.risks.size() - 1);
    }

    public static Strategy getLastAddedStrategy() {
        return st.strategies.get(st.strategies.size() - 1);
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

    public static Strategy selectStrategyByID(int id) {
        Strategy strategy = null;
        for (int i = 0; i < st.strategies.size(); i++) {
            if (st.strategies.get(i).getId() == id) {
                strategy = st.strategies.get(i);
            }
        }
        return strategy;
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
        int id = strategy.getId();
        String name = strategy.getName();
        String category = strategy.getCategory();
        String description = strategy.getDescription();

        DBFacade dbFacade = new DBFacade();
        dbFacade.insertStrategy(id, name, category, description);

    }

    public static void updateRiskDB(Risk risk) {
        String description = risk.getDescription();
        Double probability = risk.getProbability();
        Double consequence = risk.getConsequence();
        Double exposure = risk.getExposure();
        int id = risk.getId();

        DBFacade dbFacade = new DBFacade();
        dbFacade.updateRisk(description, probability, consequence, exposure, id);
    }

    public static void updateStrategyDB(Strategy strategy) {
        String name = strategy.getName();
        String category = strategy.getCategory();
        String description = strategy.getDescription();
        int id = strategy.getId();

        DBFacade dbFacade = new DBFacade();
        dbFacade.updateStrategy(name, category, description, id);
    }

    public static void linkStrategy(Risk risk, Strategy strategy) {
        rt.linkStrategy(risk, strategy);
    }

    public static void linkStrategyDB(Risk risk, Strategy strategy) {
        DBFacade dbFacade = new DBFacade();
        dbFacade.linkStrategy(strategy.getId(), risk.getId());
    }

    public ObservableList<Risk> getRiskTable() {
        return rt.risks;
    }

    public ObservableList<Strategy> getStrategyTable() {
        return st.strategies;
    }
}

