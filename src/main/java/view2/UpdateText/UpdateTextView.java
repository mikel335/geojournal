package view2.UpdateText;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateTextView extends JPanel implements ActionListener {
    final JButton saveButton = new JButton("Save");
    final JButton deleteButton = new JButton("Delete");

    TitleDescEditBoxes textEditPanel;

    public UpdateTextView() {
        // TODO implement data storage to grab constructor values
        textEditPanel = new TitleDescEditBoxes("", "");

        JPanel buttonPanel1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        // configuring buttons
        saveButton.setSize(150, 75);
        saveButton.setForeground(Color.GREEN.darker());
        saveButton.addActionListener(this);

        deleteButton.setSize(150, 75);
        deleteButton.setForeground(Color.RED);
        deleteButton.addActionListener(this);

        // Setting up the view
        buttonPanel1.add(saveButton, BorderLayout.EAST);
        buttonPanel1.add(deleteButton, BorderLayout.WEST);

        setLayout(new BorderLayout());
        add(textEditPanel, BorderLayout.NORTH); // Text area view at the top
        add(buttonPanel1, BorderLayout.CENTER); // Button panel at the bottom (aligned right)
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveButton) {
            // TODO implement confirmation message for overriding 
            saveText();
        }

        else if (e.getSource() == deleteButton) {
            // TODO implement confirmation message for deleting
        }
    }

    public void saveText() {
        String title = textEditPanel.getTitleText();
        String description = textEditPanel.getDescriptionText();

        // TODO Implement data storage
        System.out.println(title);
        System.out.println(description);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame demoFrame = new JFrame();
            demoFrame.add(new UpdateTextView());
            demoFrame.setSize(700, 500);
            demoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            demoFrame.setVisible(true);
        });
    }
}
