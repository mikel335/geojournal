package view;

import javax.swing.*;
import java.awt.*;

public class MainEntryView extends JFrame {

    private final JPanel cardPanel;

    public MainEntryView() {
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel(new BorderLayout());

        // Card selection buttons
        JPanel switchTabsButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton imagesButton = new JButton("Images");
        JButton mapButton = new JButton("Map");

        // Title panel
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setBackground(Color.CYAN);
        JLabel placeholderLabel = new JLabel("Placeholder for title and description");
        titlePanel.add(placeholderLabel);

        // Map panel
        JPanel mapPanel = new MapView(43.6532, -79.3832);

        // Image panel
        ImageView imagePanel = new ImageView();

        // Card panel to switch between map and image panels
        cardPanel = new JPanel(new CardLayout());

        cardPanel.add(imagePanel, "Images");
        cardPanel.add(mapPanel, "Map");

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

        // Add panels
        headerPanel.add(titlePanel, BorderLayout.NORTH);
        headerPanel.add(switchTabsButtonPanel, BorderLayout.SOUTH);
        add(headerPanel, BorderLayout.NORTH);
        add(cardPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainEntryView demo = new MainEntryView();
            demo.setVisible(true);
        });
    }
}
