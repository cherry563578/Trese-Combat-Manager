package gameCharacter;

public class Trese extends GameCharacter {

    private String memberName;

    public Trese(String name, int level, int health) {
        super(name, "Trese", level, health);
        this.memberName = name; 
    }

    @Override
    public String getType() { 
        if (memberName.equalsIgnoreCase("Alexandra")) return "Investigator";
        if (memberName.equalsIgnoreCase("Anton")) return "Alchemist";
        if (memberName.equalsIgnoreCase("Miranda")) return "Seer";
        return "Trese Family";
    }

    @Override
    public String attack() {
        if (memberName.equalsIgnoreCase("Alexandra")) {
            return getName() + " wields Sinag, striking with arnis and boxing combos!";
        } else if (memberName.equalsIgnoreCase("Anton")) {
            return getName() + " slashes with his magic-inscribed balisong!";
        } else if (memberName.equalsIgnoreCase("Miranda")) {
            return getName() + " predicts the enemy's exact future placement and strikes flawlessly!";
        }
        return getName() + " strikes with a magic kris!";
    }

    @Override
    public String specialMove() {
        if (memberName.equalsIgnoreCase("Alexandra")) {
            return getName() + " dons the Cloak of Takipsilim, summons fireballs, and scries via water!";
        } else if (memberName.equalsIgnoreCase("Anton")) {
            return getName() + " controls a swarm of defensive black barrier stones and seals lifeforce!";
        } else if (memberName.equalsIgnoreCase("Miranda")) {
            return getName() + " reveals branching fates, rendering herself immune to illusions and tricks!";
        }
        return getName() + " scries via water and extracts corpse memories!";
    }
}