package view.UpdateCoords;

import view.Components.Colors;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class CoordEditBoxes extends JPanel {

    private final JTextField latitudeField;
    private final JTextField longitudeField;
    private final static String LATITUDE_PLACEHOLDER = "0.0";
    private final static String LONGITUDE_PLACEHOLDER = "0.0";

    public CoordEditBoxes(String latitude, String longitude) {

        this.latitudeField = new JTextField(latitude);
        this.latitudeField.setFont(new Font("sans serif", Font.PLAIN, 20));
        this.longitudeField = new JTextField(longitude);
        this.longitudeField.setFont(new Font("sans serif", Font.PLAIN, 20));

        latitudeField.setColumns(10);
        longitudeField.setColumns(10);

        setBackground(Colors.lightBlue);

        addPlaceholder(latitudeField, LATITUDE_PLACEHOLDER);
        resetPlaceholder(latitudeField, LATITUDE_PLACEHOLDER);

        addPlaceholder(longitudeField, LONGITUDE_PLACEHOLDER);
        resetPlaceholder(longitudeField, LONGITUDE_PLACEHOLDER);

        this.latitudeField.setForeground(Color.GRAY);
        this.longitudeField.setForeground(Color.GRAY);

        latitudeField.setEditable(true);
        longitudeField.setEditable(true);

        JPanel latitudeContainer = new JPanel(new FlowLayout());
        latitudeContainer.setBackground(Colors.lightBlue);
        JLabel latitudeLabel = new JLabel("Latitude");
        latitudeLabel.setFont(new Font("sans serif", Font.PLAIN, 20));
        latitudeContainer.add(latitudeLabel);
        latitudeContainer.add(latitudeField);

        JPanel longitudeContainer = new JPanel(new FlowLayout());
        JLabel longitudeLabel = new JLabel("Longitude");
        longitudeLabel.setFont(new Font("sans serif", Font.PLAIN, 20));
        longitudeContainer.setBackground(Colors.lightBlue);
        longitudeContainer.add(longitudeLabel);
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

    /**
     * Gets the values in the latitude textbox.
     * @return The current value that the user inputted in the latitude textbox
     */
    public String getLatitudeText() {
        return latitudeField.getText();
    }

    /**
     * Gets the values in the longitude textbox.
     * @return The current value that the user inputted in the longitude textbox
     */
    public String getLongitudeText() {
        return longitudeField.getText();
    }

    /**
     * Sets the value in the latitude textbox.
     * @param lati The value of the text box to set to.
     */
    public void setLatitudeText(String lati) {
        latitudeField.setText(lati);
        resetPlaceholder(latitudeField, LATITUDE_PLACEHOLDER);
    }

    /**
     * Sets the value in the latitude textbox.
     * @param longi The value of the text box to set to.
     */
    public void setLongitudeText(String longi) {
        longitudeField.setText(longi);
        resetPlaceholder(longitudeField, LONGITUDE_PLACEHOLDER);
    }
}
