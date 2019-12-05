package logic;

class Strategy {
    String description;
    String category;

    Strategy() {
        description = "";
        category = "";
    }

    public void specifyStrategy(String description, String category) {

        this.description = description;
        this.category = category;
        RiskManagerController.addStrategyToDB(this);
    }

}
