package logic;

import domain.Strategy;
import javafx.collections.ObservableList;

class StrategyTable {

    ObservableList<Strategy> strategies;

    int lastID = 1;

    void createStrategy() {
        Strategy strategy = new Strategy(lastID);
        strategies.add(strategy);
        lastID++;
    }

    public void deleteLastAdded() {
        strategies.remove(strategies.size() - 1);
        lastID--;
    }

    public void deleteStrategy(Strategy strategy) {
        strategies.remove(strategy);
    }

    public void specifyStrategy (Strategy strategy, String name, String category, String description){
        strategy.specifyStrategy(name, category, description);
    }
}
