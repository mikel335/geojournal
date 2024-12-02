package view.UpdateText;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class TitleDescEditBoxes extends JPanel {

    private final JTextField titleTextField;
    private final JTextArea descriptionTextArea;
    private final static String TITLE_PLACEHOLDER = "Enter title ...";
    private final static String DESCRIPTION_PLACEHOLDER = "Enter description ...";

    public TitleDescEditBoxes(String title, String description) {
        
        //Setting up the view
        this.titleTextField = new JTextField(title);
        this.descriptionTextArea = new JTextArea(description);

        addPlaceholder(titleTextField, TITLE_PLACEHOLDER);
        resetPlaceholder(titleTextField, TITLE_PLACEHOLDER);

        addPlaceholder(descriptionTextArea, DESCRIPTION_PLACEHOLDER);
        resetPlaceholder(descriptionTextArea, DESCRIPTION_PLACEHOLDER);

        this.titleTextField.setForeground(Color.GRAY);
        this.descriptionTextArea.setForeground(Color.GRAY);

        titleTextField.setEditable(true);
        descriptionTextArea.setEditable(true);

        descriptionTextArea.setRows(40);

        JScrollPane titleScrollPane = new JScrollPane(titleTextField);
        JScrollPane descriptionScrollPane = new JScrollPane(descriptionTextArea);

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

    private static void resetPlaceholder(JTextComponent textComponent, String placeholder){
        if (textComponent.getText().trim().isEmpty() || textComponent.getText().trim().equals(placeholder)) {
            textComponent.setText(placeholder);
            textComponent.setForeground(Color.GRAY);
        } else {
            textComponent.setForeground(Color.BLACK);
        }
    }

    public String getTitleText() {
        return titleTextField.getText();
    }

    public String getDescriptionText() {
        return descriptionTextArea.getText();
    }

    public void setTitleText(String title) {
        titleTextField.setText(title);
        resetPlaceholder(titleTextField, TITLE_PLACEHOLDER);
    }

    public void setDescriptionText(String description) {
        descriptionTextArea.setText(description);
        resetPlaceholder(descriptionTextArea, DESCRIPTION_PLACEHOLDER);
    }
}
