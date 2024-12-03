package view.UpdateText;

import view.Components.Colors;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.text.JTextComponent;
import javax.swing.JScrollPane;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;

public class TitleDescEditBoxes extends JPanel {

    private final JTextField titleTextField;
    private final JTextArea descriptionTextArea;
    private final static String TITLE_PLACEHOLDER = "Enter title ...";
    private final static String DESCRIPTION_PLACEHOLDER = "Enter description ...";

    public TitleDescEditBoxes(String title, String description) {
        setBackground(Colors.lightBlue);

        //Setting up the view
        this.titleTextField = new JTextField(title);
        this.titleTextField.setFont(new Font("sans serif", Font.PLAIN, 20));

        this.descriptionTextArea = new JTextArea(description);
        this.descriptionTextArea.setFont(new Font("sans serif", Font.PLAIN, 20));

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

        setLayout(new BorderLayout(0, 30));
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

    /**
     * Gets the text in the title's textbox.
     * @return The text currently in the title's textfield
     */
    public String getTitleText() {
        return titleTextField.getText();
    }

    /**
     * Gets the text in the description's textfield.
     * @return The text currently in the description's textfield
     */
    public String getDescriptionText() {
        return descriptionTextArea.getText();
    }

    /**
     * Sets the text in the title's textfield.
     * @param title The text to place in the title's textfield
     */
    public void setTitleText(String title) {
        titleTextField.setText(title);
        resetPlaceholder(titleTextField, TITLE_PLACEHOLDER);
    }

    /**
     * Sets the text in the description's textfield.
     * @param description The text to place in the description's textfield
     */
    public void setDescriptionText(String description) {
        descriptionTextArea.setText(description);
        resetPlaceholder(descriptionTextArea, DESCRIPTION_PLACEHOLDER);
    }
}
