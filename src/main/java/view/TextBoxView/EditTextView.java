package view.TextBoxView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EditTextView extends JPanel implements ActionListener {

    final JButton saveButton = new JButton("Save");
    final JButton closeButton = new JButton("Cancel");
    TextAreaView textEditPanel;

    public EditTextView() {
        // TODO implement data storage to grab constructor values
        textEditPanel = new TextAreaView("", "");

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 1, 1)); // Align buttons to the right

        // configuring buttons
        saveButton.setPreferredSize(new Dimension(75, 30));
        saveButton.setForeground(Color.GREEN.darker());
        saveButton.addActionListener(this);

        closeButton.setPreferredSize(new Dimension(75, 30));
        closeButton.setForeground(Color.RED);
        closeButton.addActionListener(this);

        // Adding buttons to the panel
        buttonPanel.add(saveButton);
        buttonPanel.add(closeButton);

        // Setting up the view
        setLayout(new BorderLayout());
        add(textEditPanel, BorderLayout.NORTH); // Text area view at the top
        add(buttonPanel, BorderLayout.SOUTH); // Button panel at the bottom (aligned right)
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveButton) {
            saveText();
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
            demoFrame.add(new EditTextView());
            demoFrame.setSize(700, 500);
            demoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            demoFrame.setVisible(true);
        });
    }
}