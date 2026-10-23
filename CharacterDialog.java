package gameCharacter;

import javax.swing.*;
import java.awt.*;

public class CharacterDialog extends JDialog {
    private JTextField nameField;
    private JComboBox<String> typeBox;
    private JSpinner levelSpinner;
    private JSpinner healthSpinner;
    private boolean confirmed = false;
    private GameCharacter result;

    public CharacterDialog(Frame owner, String title, GameCharacter existing) {
        super(owner, title, true);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Name:"), gbc);
        nameField = new JTextField(15);
        gbc.gridx = 1;
        add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("Type:"), gbc);
        typeBox = new JComboBox<>(new String[]{"Trese", "Shapeshifter", "Elemental", "Aquatic"});
        gbc.gridx = 1;
        add(typeBox, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        add(new JLabel("Level (1-99):"), gbc);
        levelSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 99, 1));
        gbc.gridx = 1;
        add(levelSpinner, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        add(new JLabel("Health (1-999):"), gbc);
        healthSpinner = new JSpinner(new SpinnerNumberModel(100, 1, 999, 1));
        gbc.gridx = 1;
        add(healthSpinner, gbc);

        JPanel buttonPanel = new JPanel();
        JButton okBtn = new JButton("OK");
        JButton cancelBtn = new JButton("Cancel");
        buttonPanel.add(okBtn);
        buttonPanel.add(cancelBtn);

        gbc.gridx = 0; gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(buttonPanel, gbc);

        if (existing != null) {
            nameField.setText(existing.getName());
            typeBox.setSelectedItem(existing.getClass().getSimpleName());
            levelSpinner.setValue(existing.getLevel());
            healthSpinner.setValue(existing.getHealth());
        }

        okBtn.addActionListener(e -> onOk());
        cancelBtn.addActionListener(e -> { confirmed = false; dispose(); });

        pack();
        setLocationRelativeTo(owner);
    }

    private void onOk() {
        String name = nameField.getText().trim();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Name cannot be empty!", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int level = (int) levelSpinner.getValue();
        int health = (int) healthSpinner.getValue();
        String type = (String) typeBox.getSelectedItem();

        result = switch (type) {
        		case "Trese" -> new Trese(name, level, health);
        		case "Shapeshifter" -> new Shapeshifter(name, level, health);
        		case "Elemental" -> new Elemental(name, level, health);
        		case "Aquatic" -> new Aquatic(name, level, health);
            default -> null;
        };
        confirmed = true;
        dispose();
    }

    public boolean isConfirmed() { return confirmed; }
    public GameCharacter getResult() { return result; }
}