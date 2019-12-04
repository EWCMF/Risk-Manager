package logic;

import java.util.ArrayList;

public class RiskTable {

    ArrayList<Risk> risks = new ArrayList<Risk>();


    public Risk addRisk() {
        return null;
    }

    public void specifyRisk (Risk risk, String descripton, double probability, double consequence){
        risk.specifyRisk(descripton, probability, consequence);
    }

    public void deleteRisk(Risk risk){
        risk.delete();
        risks.remove(risk);
    }

}
