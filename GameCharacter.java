package gameCharacter;

public abstract class GameCharacter {
    private String name;
    private int level;
    private int health;
    private String faction; 

    public GameCharacter(String name, int level, int health) {
        this(name, "", level, health);
    }

    public GameCharacter(String name, String faction, int level, int health) {
        this.name = name;
        this.faction = faction;
        this.level = level;
        this.health = health;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getLevel() { return level; }
    public void setLevel(int level) { this.level = level; }
    public int getHealth() { return health; }
    public void setHealth(int health) { this.health = health; }
    public String getFaction() { return faction; }
    public void setFaction(String faction) { this.faction = faction; }

    public abstract String getType();
    public abstract String attack();
    public abstract String specialMove();

    public String getInfo() {
       
        return String.format("[%s] %s | Lv.%d | HP:%d", faction, name, level, health);
    }

    @Override
    public String toString() {
        return getInfo();
    }
}