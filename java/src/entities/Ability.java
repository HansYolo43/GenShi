package entities;

public class Ability {
    private int level;
    private final String name;
    private final String description;
    private final String type;
    private final int baseEffect;
    //private int baseCost;
    //constructors below
    public Ability(int level, String name, String description, String type, int baseEffect) {
        this.level = level;
        this.name = name;
        this.description = description;
        this.type = type;
        this.baseEffect = baseEffect;
    }
    //getters below
    public int getLevel() {
        return level;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public String getType() {
        return type;
    }
    public int getBaseEffect() {
        return baseEffect;
    }
    //setters below
    public void setLevel(int level) {
        this.level = level;
    }
}
