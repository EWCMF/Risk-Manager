package logic;

import java.util.ArrayList;

class RiskTable {

    ArrayList<Risk> risks = new ArrayList<Risk>();


    public void createRisk() {
        Risk risk = new Risk();
        risks.add(risk);
    }

    public void specifyRisk (Risk risk, String descripton, double probability, double consequence){
        risk.specifyRisk(descripton, probability, consequence);
    }

    public void deleteRisk(Risk risk){
        risk.delete();
        risks.remove(risk);
    }

    public void deleteLastAdded() {
        risks.remove(risks.size() - 1);
    }

    public void linkStrategy(Risk risk, Strategy strategy) {
        risk.linkStrategy(strategy);
    }
}
