package view.TextBoxView;

import javax.swing.*;
import java.awt.*;

import java.awt.event.*;

public class EditTextView extends JPanel implements ActionListener {

    final JButton saveButton = new JButton("Save");
    final JButton closeButton = new JButton("Close (don't save)");
    TextAreaView textEditPanel;

    public EditTextView() {
        // TODO implement data storage to grab constructor values
        textEditPanel = new TextAreaView("", "");

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        // configuring buttons
        saveButton.setSize(150, 75);
        saveButton.addActionListener(this);

        closeButton.setSize(150, 75);
        closeButton.addActionListener(this);

        // Setting up the view
        buttonPanel.add(saveButton, BorderLayout.EAST);
        buttonPanel.add(closeButton, BorderLayout.WEST);

        setLayout(new BorderLayout());
        add(textEditPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
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
