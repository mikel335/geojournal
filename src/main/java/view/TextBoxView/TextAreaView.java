package view.TextBoxView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class TextAreaView extends JPanel {

    private final JTextField titleTextField;
    private final JTextArea descriptionTextArea;

    public TextAreaView(String title, String description) {
        this.titleTextField = new JTextField(title);
        this.descriptionTextArea = new JTextArea(description);

        // Configure titleTextField
        titleTextField.setEditable(true);
        titleTextField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if (titleTextField.getText().equals("Choose a title")) {
                    titleTextField.setText("");
                }
            }

            public void focusLost(FocusEvent e) {
                if (titleTextField.getText().isEmpty()) {
                    titleTextField.setText("Choose a title");
                }
            }
        });

        // Configure descriptionTextArea
        descriptionTextArea.setEditable(true);
        descriptionTextArea.setRows(5);
        descriptionTextArea.setLineWrap(true);
        descriptionTextArea.setWrapStyleWord(true);

        // Wrap text fields in JScrollPane
        JScrollPane titleScrollPane = new JScrollPane(titleTextField);
        JScrollPane descriptionScrollPane = new JScrollPane(descriptionTextArea);

        // Use vertical BoxLayout for stacking components
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Add title field and small gap
        add(titleScrollPane, BorderLayout.NORTH);
        add(Box.createVerticalStrut(-1));

        // Add description field
        add(descriptionScrollPane);
    }

    public String getTitleText() {
        return titleTextField.getText();
    }

    public String getDescriptionText() {
        return descriptionTextArea.getText();
    }
}
