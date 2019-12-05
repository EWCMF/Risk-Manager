package logic;

import java.util.ArrayList;

class StrategyTable {

    ArrayList<Strategy> strategies = new ArrayList<>();

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

    public void specifyRisk (Strategy strategy, String description, String category){
        strategy.specifyStrategy(description, category);
    }


}
