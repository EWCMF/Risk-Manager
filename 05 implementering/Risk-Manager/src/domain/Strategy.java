package domain;

public class Strategy {
    private int id;
    private String name;
    private String description;
    private String category;

    public Strategy(int lastID) {
        id = lastID;
        name = "";
        description = "";
        category = "";
    }

    public Strategy(int id, String name, String category, String description) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
    }

    public void specifyStrategy(String name, String category, String description) {
        this.name = name;
        this.category = category;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
