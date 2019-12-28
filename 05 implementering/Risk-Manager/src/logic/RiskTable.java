package logic;

import domain.Risk;
import domain.Strategy;
import javafx.collections.ObservableList;

import java.util.ArrayList;

class RiskTable {

    ObservableList<Risk> risks;


    public void createRisk() {
        Risk risk = new Risk();
        risks.add(risk);
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
    }
}
