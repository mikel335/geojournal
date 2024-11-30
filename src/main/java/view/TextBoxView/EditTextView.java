package view.TextBoxView;

import javax.swing.*;
import java.awt.*;

import java.awt.event.*;

public class EditTextView extends JPanel implements ActionListener {
    final private JPanel textPanel;
    final JButton saveButton = new JButton("Save");
    final JButton deleteButton = new JButton("Delete");
    TextAreaView textEditPanel;

    public EditTextView() {
        // TODO implement data storage to grab constructor values
        textEditPanel = new TextAreaView("", "");

        textPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        // configuring buttons
        saveButton.setSize(150, 75);
        saveButton.setForeground(Color.GREEN.darker());
        saveButton.addActionListener(this);

        deleteButton.setSize(150, 75);
        deleteButton.setForeground(Color.RED);
        deleteButton.addActionListener(this);

        // Setting up the view
        textPanel.add(saveButton, BorderLayout.EAST);

        textPanel.add(deleteButton, BorderLayout.WEST);

        setLayout(new BorderLayout());
        add(textEditPanel, BorderLayout.NORTH);
        add(textPanel, BorderLayout.CENTER);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveButton) {
            saveText();
        }

        else if (e.getSource() == deleteButton) {

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
