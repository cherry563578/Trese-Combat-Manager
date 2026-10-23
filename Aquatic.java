package gameCharacter;

public class Aquatic extends GameCharacter {

    private String memberName;

    public Aquatic(String name, int level, int health) {
        super(name, "Aquatic", level, health);
        this.memberName = name;
    }

    @Override
    public String getType() { return "Hypnotist"; }

    @Override
    public String attack() {
        if (memberName.equalsIgnoreCase("Sirena")) {
            return getName() + " swims swiftly to evade attacks!";
        } else if (memberName.equalsIgnoreCase("Siyokoy")) {
            return getName() + " deals high underwater melee damage with razor fangs and webbed claws!";
        }
        return getName() + " slashes with webbed claws and razor fangs!";
    }

    @Override
    public String specialMove() {
        if (memberName.equalsIgnoreCase("Sirena")) {
            return getName() + " uses a hypnotic voice and song to stun, charm, and drag enemies into a helpless trance!";
        } else if (memberName.equalsIgnoreCase("Siyokoy")) {
            return getName() + " unleashes a monstrous, fully-scaled fish-humanoid frenzy!";
        }
        return getName() + " sings a hypnotic song to drag enemies into a trance!";
    }
}