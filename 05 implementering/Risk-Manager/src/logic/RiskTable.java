package logic;

import domain.Risk;
import javafx.collections.ObservableList;

class RiskTable {

    ObservableList<Risk> risks;

    int lastID;

    public void createRisk() {
        Risk risk = new Risk(lastID);
        risks.add(risk);
        lastID++;
    }

    public void specifyRisk (Risk risk, String description, double probability, double consequence){
        risk.specifyRisk(description, probability, consequence);
    }

    public void deleteRisk(Risk risk){
        risk.delete();
        risks.remove(risk);
    }

    public void deleteLastAdded() {
        risks.remove(risks.size() - 1);
        lastID--;
    }
}
