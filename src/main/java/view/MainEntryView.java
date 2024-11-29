package view;

import view.TextBoxView.EditTextView;

import javax.swing.*;
import java.awt.*;

public class MainEntryView extends JPanel {

    public MainEntryView() {
        setSize(700, 500);
        setLayout(new BorderLayout());

        /*
         **** HEADER (text, description and tab selection) ****
         */
        JPanel headerPanel = new JPanel(new BorderLayout());

        // Buttons to change displayed tab
        JPanel switchTabsButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton imagesButton = new JButton("Images");
        JButton mapButton = new JButton("Map");

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
        switchTabsButtonPanel.add(imagesButton);

        mapButton.addActionListener(_ -> {
            CardLayout cl = (CardLayout) cardPanel.getLayout();
            cl.show(cardPanel, "Map");
            imagesButton.setEnabled(true);
            mapButton.setEnabled(false);
        });
        switchTabsButtonPanel.add(mapButton);
    }

    public String getViewName() {
        return "MainEntryView";
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            MainEntryView demo = new MainEntryView();
//            demo.setVisible(true);
//        });
//    }
}
