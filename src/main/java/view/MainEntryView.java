package view;

import view.TextBoxView.EditTextView;

import javax.swing.*;
import java.awt.*;

public class MainEntryView extends JFrame {

    public MainEntryView() {
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        /*
         **** HEADER (text, description and tab selection) ****
         */
        JPanel headerPanel = new JPanel(new BorderLayout());

        // Buttons to change displayed tab
        JPanel switchTabsButtonPanel = new JPanel(new BorderLayout());
        JButton imagesButton = new JButton("Images");
        JButton mapButton = new JButton("Map");

        // Create a vertical panel to hold the buttons (align to the left)
        JPanel leftButtonPanel = new JPanel();
        leftButtonPanel.setLayout(new BoxLayout(leftButtonPanel, BoxLayout.X_AXIS));
        leftButtonPanel.add(imagesButton);
        leftButtonPanel.add(mapButton);

        switchTabsButtonPanel.add(leftButtonPanel, BorderLayout.WEST); // Add buttons to the left

        // Title panel
        EditTextView textBoxPanel = new EditTextView();
        headerPanel.add(textBoxPanel, BorderLayout.NORTH);
        headerPanel.add(switchTabsButtonPanel, BorderLayout.SOUTH);

        /*
         **** CONTENT (Different Tabs)
         */
        // Card panel to switch between map and image panels
        final JPanel cardPanel = new JPanel(new CardLayout());
        JPanel mapPanel = new MapView(43.6532, -79.3832);
        ImageView imagePanel = new ImageView();
        cardPanel.add(imagePanel, "Images");
        cardPanel.add(mapPanel, "Map");

        /*
         **** Add everything to view
         */
        add(headerPanel, BorderLayout.NORTH);
        add(cardPanel, BorderLayout.CENTER);

        /*
         **** Action Listeners ****
         */

        // Action listeners for card and image buttons
        imagesButton.addActionListener(_ -> {
            CardLayout cl = (CardLayout) cardPanel.getLayout();
            cl.show(cardPanel, "Images");
            imagesButton.setEnabled(false);
            mapButton.setEnabled(true);
        });

        mapButton.addActionListener(_ -> {
            CardLayout cl = (CardLayout) cardPanel.getLayout();
            cl.show(cardPanel, "Map");
            imagesButton.setEnabled(true);
            mapButton.setEnabled(false);
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainEntryView demo = new MainEntryView();
            demo.setVisible(true);
        });
    }
}
