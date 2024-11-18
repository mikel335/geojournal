package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextBoxView extends JFrame implements ActionListener {

    private JTextField textField;
    private JTextArea textArea;
    private final JButton editButton;

    public TextBoxView() {

        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        editButton = new JButton("Submit");
        editButton.setSize(150, 75);
        editButton.addActionListener(this);
        buttonPanel.add(editButton, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);

        textArea = new JTextArea(5, 20);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        add(scrollPane, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == editButton) {
            setSize(700, 500);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new FlowLayout(FlowLayout.LEFT));

            textField = new JTextField(20);
            textField.addActionListener(this);

            textArea = new JTextArea(5, 20);
            textArea.setEditable(false);

            String text = textField.getText();
            textArea.append(text + System.lineSeparator());
            textField.selectAll();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TextBoxView demo = new TextBoxView();
            demo.setVisible(true);
        });
    }
}
