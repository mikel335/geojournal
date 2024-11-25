package view.TextBoxView;

import javax.swing.*;
import java.awt.*;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class TextAreaView extends JPanel {

    JTextField titleTextField;
    JTextArea descriptionTextArea;

    public TextAreaView(String title, String description) {

        this.titleTextField = new JTextField(title);
        this.descriptionTextArea = new JTextArea(description);

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

        descriptionTextArea.setEditable(true);
        descriptionTextArea.setRows(5);

        JScrollPane titleScrollPane = new JScrollPane(titleTextField);
        JScrollPane descriptionScrollPane = new JScrollPane(descriptionTextArea);

        setLayout(new BorderLayout());
        add(titleScrollPane, BorderLayout.NORTH);
        add(descriptionScrollPane, BorderLayout.CENTER);
    }

    public String getTitleText() {
        return titleTextField.getText();
    }

    public String getDescriptionText() {
        return descriptionTextArea.getText();
    }

}
