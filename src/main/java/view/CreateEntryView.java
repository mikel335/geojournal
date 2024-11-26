package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateEntryView extends JFrame {
    //private final JPanel;
    public CreateEntryView() {
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // create new entry button
        final JButton entryButton = new JButton("New Entry");
        add(entryButton, BorderLayout.NORTH);
        entryButton.setVisible(true);
        entryButton.addActionListener(this::actionPerformed);

    }

    public void actionPerformed(ActionEvent e) {
        MainEntryView entry = new MainEntryView();
        entry.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CreateEntryView demo = new CreateEntryView();
            demo.setVisible(true);
        });
    }
}
