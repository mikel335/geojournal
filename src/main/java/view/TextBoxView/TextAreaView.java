package view.TextBoxView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.text.JTextComponent;

public class TextAreaView extends JPanel {

    private final JTextField titleTextField;
    private final JTextArea descriptionTextArea;

    public TextAreaView(String title, String description) {

        this.titleTextField = new JTextField("Enter your title here...");
        this.descriptionTextArea = new JTextArea("Enter your description here...");
        this.titleTextField.setForeground(Color.GRAY);
        this.descriptionTextArea.setForeground(Color.GRAY);
        this.titleTextField = new JTextField(title);
        this.descriptionTextArea = new JTextArea(description);

        // Configure titleTextField
        titleTextField.setEditable(true);
        descriptionTextArea.setEditable(true);

        descriptionTextArea.setRows(20);
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

        addPlaceholder(titleTextField, "Enter your title here...");
        addPlaceholder(descriptionTextArea, "Enter your description here...");

        setLayout(new BorderLayout());
        // Use vertical BoxLayout for stacking components
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Add title field and small gap
        add(titleScrollPane, BorderLayout.NORTH);
        add(Box.createVerticalStrut(-1));

        // Add description field
        add(descriptionScrollPane);
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
