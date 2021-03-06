package domain;

public class Risk {
    private int id;
    private String description;
    private double probability;
    private double consequence;
    private double exposure;
    private boolean hasStrategy;
    private Strategy strategy;

    public Risk(int lastID) {
        id = lastID;
        description = "";
        probability = 0.0;
        consequence = 0.0;
        hasStrategy = false;
        strategy = null;
    }

    public Risk(int id, String description, double probability, double consequence, double exposure, Strategy strategy, boolean hasStrategy) {
        this.id = id;
        this.description = description;
        this.probability = probability;
        this.consequence = consequence;
        this.exposure = exposure;
        this.strategy = strategy;
        this.hasStrategy = hasStrategy;
    }

    public void specifyRisk(String description, double probability, double consequence) {
        if (probability < 0 || probability > 100 || consequence < 0 || consequence > 100) {
            System.out.println("Ugyldig risiko værdier");
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }

    public double getConsequence() {
        return consequence;
    }

    public void setConsequence(double consequence) {
        this.consequence = consequence;
    }

    public double getExposure() {
        return exposure;
    }

    public void setExposure(double exposure) {
        this.exposure = exposure;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public boolean getHasStrategy() {
        return hasStrategy;
    }

    public void setHasStrategy(boolean hasStrategy) {
        this.hasStrategy = hasStrategy;
    }
}
