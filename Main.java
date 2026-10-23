package gameCharacter;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {
    public static void main(String[] args) {
 
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {}
       
            GameCharacterGUI gui = new GameCharacterGUI();
            gui.setVisible(true);
        });
    }
}