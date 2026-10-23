package gameCharacter;

public class Shapeshifter extends GameCharacter {

    private String memberName;

    public Shapeshifter(String name, int level, int health) {
        super(name, "Shapeshifter", level, health);
        this.memberName = name;
    }

    @Override
    public String getType() { return "Striker"; }

    @Override
    public String attack() {
        if (memberName.equalsIgnoreCase("Manananggal")) {
            return getName() + " attacks with sharp claws, fangs, and a long prehensile tongue!";
        } else if (memberName.equalsIgnoreCase("Sigbin")) {
            return getName() + " shifts into a massive shadow-hound and lunges with high agility!";
        }
        return getName() + " lunges with fangs and a prehensile tongue!";
    }

    @Override
    public String specialMove() {
        if (memberName.equalsIgnoreCase("Manananggal")) {
            return getName() + " detaches their upper torso to fly via bat wings! (Weak to salt/sunlight)";
        } else if (memberName.equalsIgnoreCase("Sigbin")) {
            return getName() + " walks backward to project shadow energy and tracks by blood and fear!";
        }
        return getName() + " detaches their torso to fly via bat wings!";
    }
}