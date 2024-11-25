package view;

import javax.swing.*;
import java.awt.*;

public class MainEntryView extends JFrame {

    private final JPanel cardPanel;

    public MainEntryView() {
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Title panel
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setBackground(Color.CYAN);
        JLabel placeholderLabel = new JLabel("Placeholder for title and description");
        titlePanel.add(placeholderLabel);

        // Map panel
        JPanel mapPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        mapPanel.setBackground(Color.RED);
        JLabel mapLabel = new JLabel("Placeholder for map");
        mapPanel.setPreferredSize(new Dimension(600, 400));
        mapPanel.add(mapLabel);

        // Image panel
        ImageView imagePanel = new ImageView();

        // Card panel to switch between map and image panels
        cardPanel = new JPanel(new CardLayout());
        cardPanel.add(mapPanel, "Map");
        cardPanel.add(imagePanel, "Images");

        // Map and images buttons
        JPanel switchTabsButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton imagesButton = new JButton("Images");
        imagesButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) cardPanel.getLayout();
            cl.show(cardPanel, "Images");
        });
        switchTabsButtonPanel.add(imagesButton);

        JButton mapButton = new JButton("Map");
        mapButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) cardPanel.getLayout();
            cl.show(cardPanel, "Map");
        });
        switchTabsButtonPanel.add(mapButton);

        // Add panels
        add(titlePanel, BorderLayout.NORTH);
        add(switchTabsButtonPanel, BorderLayout.CENTER);
        add(cardPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainEntryView demo = new MainEntryView();
            demo.setVisible(true);
        });
    }
}
