package logic;

public class RiskManagerController {
    RiskTable rt = new RiskTable();
    StrategyTable st = new StrategyTable();

    public void createRisk() {
        rt.createRisk();
    }

    public void specifyRisk(Risk risk, String description, double probability, double consequence) {
        rt.specifyRisk(risk, description, probability, consequence);
    }

    public void linkStrategy(Risk risk, Strategy strategy) {
        rt.linkStrategy(risk, strategy);
    }

    public void deleteRisk(Risk risk) {
        rt.deleteRisk(risk);
    }

}
