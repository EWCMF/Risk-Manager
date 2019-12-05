package logic;

class Strategy {
    String name;
    String description;
    String category;

    Strategy() {
        name = "";
        description = "";
        category = "";
    }

    public void specifyStrategy(String name, String description, String category) {

        this.name = name;
        this.description = description;
        this.category = category;
    }

}
