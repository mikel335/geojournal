package viewWithCA.UpdateCoords;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class CoordEditBoxes extends JPanel {

    private final JTextField latitudeField;
    private final JTextField longitudeField;
    private final static String LATITUDE_PLACEHOLDER = "0.0";
    private final static String LONGITUDE_PLACEHOLDER = "0.0";

    public CoordEditBoxes(String latitude, String longitude) {

        this.latitudeField = new JTextField(latitude);
        this.longitudeField = new JTextField(longitude);

        addPlaceholder(latitudeField, LATITUDE_PLACEHOLDER);
        resetPlaceholder(latitudeField, LATITUDE_PLACEHOLDER);

        addPlaceholder(longitudeField, LONGITUDE_PLACEHOLDER);
        resetPlaceholder(longitudeField, LONGITUDE_PLACEHOLDER);

        this.latitudeField.setForeground(Color.GRAY);
        this.longitudeField.setForeground(Color.GRAY);

        latitudeField.setEditable(true);
        longitudeField.setEditable(true);

        JPanel latitudeContainer = new JPanel(new FlowLayout());
        latitudeContainer.add(new JLabel("Latitude"));
        latitudeContainer.add(latitudeField);

        JPanel longitudeContainer = new JPanel(new FlowLayout());
        longitudeContainer.add(new JLabel("Longitude"));
        longitudeContainer.add(longitudeField);

        setLayout(new FlowLayout(FlowLayout.CENTER));
        add(latitudeContainer);
        add(longitudeContainer);
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

    public String getLatitudeText() {
        return latitudeField.getText();
    }

    public String getLongitudeText() {
        return longitudeField.getText();
    }

    public void setLatitudeText(String title) {
        latitudeField.setText(title);
        resetPlaceholder(latitudeField, LATITUDE_PLACEHOLDER);
    }

    public void setLongitudeText(String description) {
        longitudeField.setText(description);
        resetPlaceholder(longitudeField, LONGITUDE_PLACEHOLDER);
    }
}
