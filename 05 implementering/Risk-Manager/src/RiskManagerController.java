public class RiskManagerController {
    RiskTable rt = new RiskTable();
    StrategyTable st = new StrategyTable();

    public Risk addRisk() {
        return null;
    }

    public void specifyRisk(Risk risk, String description, double probability, double consequence) {
        rt.specifyRisk(risk, description, probability, consequence);
    }

    public void linkStrategy(Risk risk, Strategy strategy) {

    }

    public void deleteRisk(Risk risk) {
        deleteRisk(risk);
    }
}
