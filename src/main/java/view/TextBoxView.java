package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextBoxView extends JFrame implements ActionListener {

    private JTextField textField;
    private JTextArea textArea;
    private final JButton editButton;
    private final JButton closeButton;

    public TextBoxView() {

        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        editButton = new JButton("Save");
        editButton.setSize(150, 75);
        editButton.addActionListener(this);

        closeButton = new JButton("Close (don't save)");
        closeButton.setSize(150, 75);
        closeButton.addActionListener(this);

        buttonPanel.add(editButton, BorderLayout.EAST);
        buttonPanel.add(closeButton, BorderLayout.WEST);
        add(buttonPanel, BorderLayout.SOUTH);

        textArea = new JTextArea("Edit description here");
        textArea.setEditable(true);
        JScrollPane scrollPane = new JScrollPane(textArea);

        textField = new JTextField("Edit title here");
        textField.setEditable(true);

        JScrollPane scrollPane1 = new JScrollPane(textField);


        add(scrollPane1, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == editButton) {
            String descText = textArea.getText();
            String titleText = textField.getText();
            // Must save above Strings somewhere
        }
        else if (e.getSource() == closeButton) {
            TextBoxView.this.dispose();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TextBoxView demo = new TextBoxView();
            demo.setVisible(true);
        });
    }
}
