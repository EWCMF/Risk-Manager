public class Risk {
    String description;
    double probability;
    double consequence;
    double exposure;
    Strategy strategy = new Strategy();

    public void specifyRisk(String description, double probability, double consequence) {
        if (probability < 0 || probability > 100 || consequence < 0 || consequence > 20) {
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
        exposure = probability * (consequence / 20);
    }

    public void delete() {
        strategy = null;
    }
}
