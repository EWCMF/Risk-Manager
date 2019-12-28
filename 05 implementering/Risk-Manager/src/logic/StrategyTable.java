package logic;

import domain.Strategy;
import javafx.collections.ObservableList;
import persistence.DBFacade;

import java.util.ArrayList;

class StrategyTable {

    ObservableList<Strategy> strategies;

    void createStrategy() {
        Strategy strategy = new Strategy();
        strategies.add(strategy);
    }

    static Strategy linkStrategy(Strategy strategy) {
        return null;
    }

    public void deleteLastAdded() {
        strategies.remove(strategies.size() - 1);
    }

    public void specifyStrategy (int id, String name, String description, String category){
        Strategy strategy = new Strategy();
        strategy.specifyStrategy(id, name, description, category);
    }
}
