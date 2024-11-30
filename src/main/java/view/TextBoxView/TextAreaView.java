package view.TextBoxView;

import javax.swing.*;
import java.awt.*;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.text.JTextComponent;

public class TextAreaView extends JPanel {

    JTextField titleTextField;
    JTextArea descriptionTextArea;

    public TextAreaView(String title, String description) {

        this.titleTextField = new JTextField("Enter your title here...");
        this.descriptionTextArea = new JTextArea("Enter your description here...");
        this.titleTextField.setForeground(Color.GRAY);
        this.descriptionTextArea.setForeground(Color.GRAY);

        titleTextField.setEditable(true);
        descriptionTextArea.setEditable(true);

        descriptionTextArea.setRows(20);

        JScrollPane titleScrollPane = new JScrollPane(titleTextField);
        JScrollPane descriptionScrollPane = new JScrollPane(descriptionTextArea);

        addPlaceholder(titleTextField, "Enter your title here...");
        addPlaceholder(descriptionTextArea, "Enter your description here...");

        setLayout(new BorderLayout());
        add(titleScrollPane, BorderLayout.NORTH);
        add(descriptionScrollPane, BorderLayout.CENTER);
    }
        private static void addPlaceholder(JTextComponent textComponent, String placeholder){
            textComponent.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    // Clear the placeholder text when the component gains focus
                    if (textComponent.getText().equals(placeholder)) {
                        textComponent.setText("");
                        textComponent.setForeground(Color.BLACK); // Reset text color
                    }
                }

                @Override
                public void focusLost(FocusEvent e) {
                    // Restore the placeholder text if the component is empty
                    if (textComponent.getText().trim().isEmpty()) {
                        textComponent.setText(placeholder);
                        textComponent.setForeground(Color.GRAY); // Placeholder text color
                    }
                }
            });
        }


    public String getTitleText() {
        return titleTextField.getText();
    }

    public String getDescriptionText() {
        return descriptionTextArea.getText();
    }

}
