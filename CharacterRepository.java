package gameCharacter;

import java.util.ArrayList;
import java.util.List;

/**
 * ADT: List-based repository to manage game characters.
 * Supports add, remove, search, update, and traversal.
 */
public class CharacterRepository {
    private final List<GameCharacter> characters = new ArrayList<>();

    public void add(GameCharacter c) {
        if (c == null) throw new IllegalArgumentException("Character cannot be null");
        characters.add(c);
    }

    public boolean remove(String name) {
        return characters.removeIf(c -> c.getName().equalsIgnoreCase(name));
    }

    public GameCharacter search(String name) {
        for (GameCharacter c : characters) {
            if (c.getName().equalsIgnoreCase(name)) return c;
        }
        return null;
    }

    public boolean update(String oldName, GameCharacter newChar) {
        for (int i = 0; i < characters.size(); i++) {
            if (characters.get(i).getName().equalsIgnoreCase(oldName)) {
                characters.set(i, newChar);
                return true;
            }
        }
        return false;
    }

    public List<GameCharacter> getAll() {
        return new ArrayList<>(characters);
    }

    public int size() {
        return characters.size();
    }

    public void clear() {
        characters.clear();
    }
}