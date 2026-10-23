package gameCharacter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class GameCharacterGUI extends JFrame {
    // Theme colors
	private static final Color BG_DARK = new Color(15, 15, 19, 220);    
	private static final Color PANEL_COLOR = new Color(26, 26, 36, 220);
	private static final Color ACCENT = new Color(230, 57, 70);         
	private static final Color ACCENT2 = new Color(238, 220, 130);        
	private static final Color TEXT_COLOR = new Color(241, 250, 238); 

    private final CharacterRepository repo = new CharacterRepository();
    private DefaultListModel<GameCharacter> listModel;
    private JList<GameCharacter> characterList;
    private JTextArea outputArea;
    private JLabel statusLabel;

    public GameCharacterGUI() {
        super("⚔ Trese Combat Manager⚔");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(850, 550);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(BG_DARK);
        
        BackgroundPanel bgPanel = new BackgroundPanel();
        bgPanel.setLayout(new BorderLayout(10, 10));
        
        setContentPane(bgPanel);

        initComponents();
        loadSampleData();
    }

    private Icon getCharacterIcon(GameCharacter gc) {
        int size = 40; 

        try {

        		java.net.URL imgURL = getClass().getResource("/" + gc.getName() + ".png");
            if (imgURL == null) {
                imgURL = new java.io.File(gc.getName() + ".png").toURI().toURL();
            }
            
            if (imgURL != null) {
                Image img = new ImageIcon(imgURL).getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH);
                return new ImageIcon(img);
            }
        } catch (Exception e) {

        }

        java.awt.image.BufferedImage img = new java.awt.image.BufferedImage(size, size, java.awt.image.BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        switch (gc.getFaction()) {
            case "Trese" -> g2.setColor(new Color(230, 57, 70));       
            case "Shapeshifter" -> g2.setColor(new Color(166, 227, 161)); 
            case "Elemental" -> g2.setColor(new Color(100, 100, 100)); 
            case "Aquatic" -> g2.setColor(new Color(137, 180, 250));   
            default -> g2.setColor(Color.WHITE);
        }

        g2.fillRoundRect(0, 0, size, size, 10, 10);
        g2.dispose();
        return new ImageIcon(img);
    }
    
    private void initComponents() {
        // ===== Top: Title =====
        JLabel title = new JLabel();
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBorder(new EmptyBorder(10, 0, 10, 0));

        try {
            ImageIcon titleIcon = new ImageIcon("title.png"); 
            Image scaledImage = titleIcon.getImage().getScaledInstance(300, 80, Image.SCALE_SMOOTH);
            title.setIcon(new ImageIcon(scaledImage));
        } catch (Exception e) {
            System.err.println("Could not load title image.");
            title.setText("⚔ Game Character Manager ⚔");
            title.setFont(new Font("Segoe UI Emoji", Font.BOLD, 26));
            title.setForeground(ACCENT);
        }
        add(title, BorderLayout.NORTH);

        // ===== Left: Character List =====
        listModel = new DefaultListModel<>();
        characterList = new JList<>(listModel);
        characterList.setBackground(new Color(49, 50, 68, 200)); 
        characterList.setForeground(TEXT_COLOR);
        characterList.setFont(new Font("Consolas", Font.PLAIN, 14));
        characterList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        characterList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value,
                    int index, boolean isSelected, boolean cellHasFocus) {
                
                JPanel panel = new JPanel(new BorderLayout(10, 0));
                panel.setOpaque(true); 
                panel.setBorder(new EmptyBorder(5, 5, 5, 5));

                if (value instanceof GameCharacter gc) {
                    
                    JLabel iconLabel = new JLabel(getCharacterIcon(gc));
                    iconLabel.setOpaque(false); 
                    panel.add(iconLabel, BorderLayout.WEST);

                    JLabel textLabel = new JLabel();
                    textLabel.setOpaque(false); 
                    textLabel.setFont(new Font("Consolas", Font.PLAIN, 14));
                    
                    String nameColor = isSelected ? "black" : "white";
                    String factionColor = isSelected ? "#333333" : "#AAAAAA"; 
                    
                    textLabel.setText("<html><b><font color='" + nameColor + "'>" + gc.getName() + 
                                      "</font></b><br><font size='2' color='" + factionColor + "'>" + 
                                      gc.getFaction() + "</font></html>");
                    
                    panel.add(textLabel, BorderLayout.CENTER);
                }

                if (isSelected) {
                    panel.setBackground(ACCENT2); 
                } else {
                  
                    panel.setBackground(new Color(49, 50, 68, 200)); 
                }

                return panel;
            }
        });
               
        JScrollPane listScroll = new JScrollPane(characterList);
        listScroll.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(ACCENT2), "Characters",
                0, 0, new Font("Segoe UI Emoji", Font.BOLD, 14), ACCENT2));
        listScroll.setPreferredSize(new Dimension(350, 0));
        listScroll.setOpaque(false);
        listScroll.getViewport().setOpaque(false);
        listScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // ===== Right: Output + Buttons =====
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setBackground(new Color(49, 50, 68, 200)); 
        outputArea.setForeground(TEXT_COLOR);
        outputArea.setFont(new Font("Consolas", Font.PLAIN, 14));
        outputArea.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        outputArea.setLineWrap(true);      
        outputArea.setWrapStyleWord(true); 

        JScrollPane outputScroll = new JScrollPane(outputArea);
        outputScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        outputScroll.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(ACCENT2), "Battle Log",
                0, 0, new Font("Segoe UI", Font.BOLD, 14), ACCENT2));
        outputScroll.setOpaque(false);
        outputScroll.getViewport().setOpaque(false);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 3, 8, 8));
        buttonPanel.setOpaque(false); 
        buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        Color colorAdd = new Color(166, 227, 161);    
        Color colorView = new Color(137, 180, 250);  
        Color colorSearch = new Color(137, 180, 250);
        Color colorUpdate = new Color(238, 220, 130); 
        Color colorRemove = new Color(243, 139, 168); 
        Color colorAttack = new Color(243, 139, 168); 

        JButton addBtn = createButton("➕ Add", colorAdd);
        JButton viewBtn = createButton("👁 View", colorView);
        JButton searchBtn = createButton("🔍 Search", colorSearch);
        JButton updateBtn = createButton("✏ Update", colorUpdate);
        JButton removeBtn = createButton("🗑 Remove", colorRemove);
        JButton actionBtn = createButton("⚡ Attack", colorAttack);

        buttonPanel.add(addBtn);
        buttonPanel.add(viewBtn);
        buttonPanel.add(searchBtn);
        buttonPanel.add(updateBtn);
        buttonPanel.add(removeBtn);
        buttonPanel.add(actionBtn);

        JPanel rightPanel = new JPanel(new BorderLayout(5, 5));
        rightPanel.setOpaque(false);
        rightPanel.add(outputScroll, BorderLayout.CENTER);
        rightPanel.add(buttonPanel, BorderLayout.SOUTH);

        // ===== Split pane =====
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                listScroll, rightPanel);
        splitPane.setDividerLocation(330);
        splitPane.setOpaque(false);
        splitPane.setBorder(new EmptyBorder(5, 10, 5, 10));
        add(splitPane, BorderLayout.CENTER);

        // ===== Status bar =====
        statusLabel = new JLabel(" Ready. Characters: 0", SwingConstants.LEFT);
        statusLabel.setForeground(TEXT_COLOR);
        statusLabel.setBackground(PANEL_COLOR);
        statusLabel.setOpaque(true);
        statusLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 12));
        statusLabel.setBorder(new EmptyBorder(5, 10, 5, 10));
        add(statusLabel, BorderLayout.SOUTH);

        // ===== Wire up actions =====
        addBtn.addActionListener(e -> onAdd());
        viewBtn.addActionListener(e -> onView());
        searchBtn.addActionListener(e -> onSearch());
        updateBtn.addActionListener(e -> onUpdate());
        removeBtn.addActionListener(e -> onRemove());
        actionBtn.addActionListener(e -> onAction());
    }

    private JButton createButton(String text, Color bg) {
        JButton btn = new JButton(text);
        
        btn.setContentAreaFilled(false);
        btn.setOpaque(true); 
        btn.setBackground(bg);
        
        
        btn.setFocusPainted(false);
        btn.setFont(new Font("Segoe UI Emoji", Font.BOLD, 13));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btn.setBorder(BorderFactory.createLineBorder(bg.darker(), 2));

        btn.getModel().addChangeListener(e -> {
            ButtonModel model = btn.getModel();
            if (model.isPressed()) {
   
                btn.setBackground(bg.darker());
            } else if (model.isRollover()) {
          
                btn.setBackground(bg.brighter());
            } else {
           
                btn.setBackground(bg);
            }
        });

        return btn;
    }
    // ===== Action handlers =====
    private void onAdd() {
        CharacterDialog dlg = new CharacterDialog(this, "Add Character", null);
        dlg.setVisible(true);
        if (dlg.isConfirmed()) {
            GameCharacter c = dlg.getResult();
            if (repo.search(c.getName()) != null) {
                JOptionPane.showMessageDialog(this,
                        "A character with name '" + c.getName() + "' already exists!",
                        "Duplicate Name", JOptionPane.WARNING_MESSAGE);
                return;
            }
            repo.add(c);
            refreshList();
            log("✅ Added " + c.getInfo());
            setStatus("Added character: " + c.getName());
        }
    }

    private void onView() {
        List<GameCharacter> all = repo.getAll();
        if (all.isEmpty()) {
            log("⚠ No characters to view.");
            return;
        }
        log("═══════ ALL CHARACTERS (" + all.size() + ") ═══════");
        for (GameCharacter c : all) {
            
            log("Name: " + c.getName() + " | Type: " + c.getType() + " | Lv." + c.getLevel() + " | HP:" + c.getHealth());
            log("    Attack: " + c.attack());
            log("    Special: " + c.specialMove());
            log("");
        }
        setStatus("Viewed " + all.size() + " character(s).");
    }
    
    private void onSearch() {
        String name = JOptionPane.showInputDialog(this, "Enter character name to search:");
        if (name == null || name.trim().isEmpty()) return;
        GameCharacter c = repo.search(name.trim());
        if (c == null) {
            log("❌ Character '" + name + "' not found.");
            setStatus("Search failed: not found.");
        } else {
            log("🔍 Found: " + c.getInfo());
            setStatus("Found: " + c.getName());
        }
    }

    private void onUpdate() {
        GameCharacter selected = characterList.getSelectedValue();
        if (selected == null) {
            JOptionPane.showMessageDialog(this, "Please select a character to update.",
                    "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }
        CharacterDialog dlg = new CharacterDialog(this, "Update Character", selected);
        dlg.setVisible(true);
        if (dlg.isConfirmed()) {
            repo.update(selected.getName(), dlg.getResult());
            refreshList();
            log("✏ Updated " + selected.getName() + " → " + dlg.getResult().getInfo());
            setStatus("Updated character: " + dlg.getResult().getName());
        }
    }

    private void onRemove() {
        GameCharacter selected = characterList.getSelectedValue();
        if (selected == null) {
            JOptionPane.showMessageDialog(this, "Please select a character to remove.",
                    "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this,
                "Remove character '" + selected.getName() + "'?",
                "Confirm Remove", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            repo.remove(selected.getName());
            refreshList();
            log("🗑 Removed " + selected.getName());
            setStatus("Removed character: " + selected.getName());
        }
    }

    private void onAction() {
        GameCharacter selected = characterList.getSelectedValue();
        if (selected == null) {
            JOptionPane.showMessageDialog(this, "Please select a character first.",
                    "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }
     
        log("──── " + selected.getName() + " (Lv." + selected.getLevel() + " | HP:" + selected.getHealth() + ") ────");
        log("   " + selected.attack());
        log("   " + selected.specialMove());
        log(""); 
        
        setStatus(selected.getName() + " performed actions.");
    }

    // ===== Helpers =====
    private void refreshList() {
        listModel.clear();
        for (GameCharacter c : repo.getAll()) listModel.addElement(c);
        statusLabel.setText(" Ready. Characters: " + repo.size());
    }

    private void log(String msg) {
        outputArea.append(msg + "\n");
        outputArea.setCaretPosition(outputArea.getDocument().getLength());
    }

    private void setStatus(String msg) {
        statusLabel.setText(" " + msg + "  |  Total: " + repo.size());
    }

    private void loadSampleData() {
    		repo.add(new Trese("Alexandra", 15, 120));
        repo.add(new Trese("Anton", 12, 100));
        repo.add(new Trese("Miranda", 14, 100));
        repo.add(new Shapeshifter("Manananggal", 10, 150));
        repo.add(new Shapeshifter("Sigbin", 9, 110));
        repo.add(new Elemental("Kapre", 18, 300)); 
        repo.add(new Elemental("Duwende", 8, 80));
        repo.add(new Aquatic("Sirena", 11, 90));
        repo.add(new Aquatic("Siyokoy", 13, 140));
        refreshList();
        log("When the sun goes down in Manila, the old laws bleed. Lock, load, and watch the shadows—welcome to the underworld.");
        log("Loaded " + repo.size() + " sample characters.\n");
    }
    
    private class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel() {
            try {
                this.backgroundImage = new ImageIcon("bg2.gif").getImage();
            } catch (Exception e) {
                System.err.println("Couldn't find file: bg2.gif");
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
          
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
}