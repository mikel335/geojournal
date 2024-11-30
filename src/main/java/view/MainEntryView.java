package view;

import view.TextBoxView.EditTextView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainEntryView extends JFrame {

    public MainEntryView() {
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        /*
         **** HEADER (text, description and tab selection) ****
         */
        JPanel headerPanel = new JPanel(new GridLayout());

        // Buttons to change displayed tab
        JPanel switchTabsButtonPanel = new JPanel(new BorderLayout());
        switchTabsButtonPanel.setLayout(new BoxLayout(switchTabsButtonPanel, BoxLayout.X_AXIS));
        JButton imagesButton = new JButton("Images");
        JButton mapButton = new JButton("Map");
        JButton editButton = new JButton("Edit Title and Description");


        headerPanel.add(switchTabsButtonPanel, BorderLayout.SOUTH);
        /*
         **** CONTENT (Different Tabs)
         */
        // Card panel to switch between map, image, and text panels on the left
        final JPanel cardPanel = new JPanel(new CardLayout());
        cardPanel.setPreferredSize(new Dimension(400, 250));
        JPanel mapPanel = new MapView(43.6532, -79.3832);
        ImageView imagePanel = new ImageView();
        EditTextView editPanel = new EditTextView();
        cardPanel.add(imagePanel, "Images");
        cardPanel.add(mapPanel, "Map");
        cardPanel.add(editPanel, "Edit Title and Description");

        // View panel to display title and description on the right
        final JPanel viewPanel = new JPanel(new BorderLayout());
        viewPanel.setPreferredSize(new Dimension(300, 250));
        TextField filler = new TextField("Filler");
        viewPanel.add(filler);

        /*
         **** Add everything to view
         */
        add(headerPanel, BorderLayout.NORTH);
        add(cardPanel, BorderLayout.WEST);
        add(viewPanel, BorderLayout.CENTER);

        /*
         **** Action Listeners ****
         */

        // Action listeners for card and image and text buttons
        imagesButton.addActionListener(_ -> {
            CardLayout cl = (CardLayout) cardPanel.getLayout();
            cl.show(cardPanel, "Images");
            imagesButton.setEnabled(false);
            mapButton.setEnabled(true);
            editButton.setEnabled(true);
        });
        switchTabsButtonPanel.add(imagesButton);

        mapButton.addActionListener(_ -> {
            CardLayout cl = (CardLayout) cardPanel.getLayout();
            cl.show(cardPanel, "Map");
            imagesButton.setEnabled(true);
            mapButton.setEnabled(false);
            editButton.setEnabled(true);
        });
        switchTabsButtonPanel.add(mapButton);

        editButton.addActionListener(_ -> {
            CardLayout cl = (CardLayout) cardPanel.getLayout();
            cl.show(cardPanel, "Edit Title and Description");
            imagesButton.setEnabled(true);
            mapButton.setEnabled(true);
            editButton.setEnabled(false);
        });
        switchTabsButtonPanel.add(editButton);


    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainEntryView demo = new MainEntryView();
            demo.setVisible(true);
        });
    }
}
