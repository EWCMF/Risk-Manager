package logic;

import java.util.ArrayList;

public class RiskTable {

    ArrayList<Risk> risks = new ArrayList<Risk>();


    public Risk createRisk() {
        Risk risk = new Risk();
        risks.add(risk);
        return risk;
    }

    public void specifyRisk (Risk risk, String descripton, double probability, double consequence){
        risk.specifyRisk(descripton, probability, consequence);
    }

    public void deleteRisk(Risk risk){
        risk.delete();
        risks.remove(risk);
    }

    public void linkStrategy(Risk risk, Strategy strategy) {
        risk.linkStrategy(strategy);
    }
}
