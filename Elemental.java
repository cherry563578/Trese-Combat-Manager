package gameCharacter;

public class Elemental extends GameCharacter {

    private String memberName;

    public Elemental(String name, int level, int health) {
        super(name, "Elemental", level, health);
        this.memberName = name;
    }

    @Override
    public String getType() { return "Tank"; }

    @Override
    public String attack() {
        if (memberName.equalsIgnoreCase("Kapre")) {
            return getName() + " uses brute-force strength, shrugging off damage with high durability!";
        } else if (memberName.equalsIgnoreCase("Duwende")) {
            return getName() + " submerges into the floor instantly and summons a Laman Lupa mud golem!";
        }
        return getName() + " summons a massive Laman Lupa mud golem!";
    }

    @Override
    public String specialMove() {
        if (memberName.equalsIgnoreCase("Kapre")) {
            return getName() + " manipulates cigar smoke to cast disorienting illusions and obscure the battlefield!";
        } else if (memberName.equalsIgnoreCase("Duwende")) {
            return getName() + " casts status-ailment curses, causing swelling and blindness!";
        }
        return getName() + " obscures the battlefield with disorienting cigar smoke!";
    }
}