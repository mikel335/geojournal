package view;

import javax.swing.*;
        import java.awt.*;
        import java.awt.event.ActionEvent;
import java.util.HashMap;
import entities.Entry;

public class CreateEntryView extends JFrame {
    private int identification = 0;
    private int placeholderEntry = 0;
    private HashMap<Integer, Entry> entryList = new HashMap<>();

    public CreateEntryView() {
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create new entry button
        final JButton entryButton = new JButton("New Entry");
        add(entryButton, BorderLayout.NORTH);
        entryButton.setVisible(true);
        entryButton.addActionListener(this::actionPerformed);
    }

    public void actionPerformed(ActionEvent e) {
        MainEntryView entryView = new MainEntryView();
        entryView.setVisible(true);
        identification++;
        placeholderEntry++;
        Entry newEntry = new Entry(identification, placeholderEntry);
        entryList.put(identification, newEntry);
    }

    // This file will be combined with other file so psvm will get deleted
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CreateEntryView demo = new CreateEntryView();
            demo.setVisible(true);
        });
    }
}
