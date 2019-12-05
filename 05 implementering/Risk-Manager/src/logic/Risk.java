package logic;

class Risk {
    String description;
    double probability;
    double consequence;
    double exposure;
    Strategy strategy = new Strategy();

    Risk() {
        description = "";
        probability = 0.0;
        consequence = 0.0;
    }

    public void specifyRisk(String description, double probability, double consequence) {
        if (probability < 0 || probability > 100 || consequence < 0 || consequence > 100) {
            //Invalid risk
        }
        else {
            this.description = description;
            this.probability = probability;
            this.consequence = consequence;
            calculateExposure();
        }

    }
    private void calculateExposure() {
        exposure = probability * consequence / 100;
    }

    public void delete() {
        strategy = null;
    }

    public void linkStrategy(Strategy strategy) {
        strategy = StrategyTable.linkStrategy(strategy);
    }
}
