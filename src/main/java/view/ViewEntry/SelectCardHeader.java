package view.ViewEntry;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.CardLayout;
import java.awt.FlowLayout;

public class SelectCardHeader extends JPanel {

    public SelectCardHeader(JPanel contentCards) {
        setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton imagesButton = new JButton("Images");
        JButton mapButton = new JButton("Map");
        imagesButton.setEnabled(false);

        // Action listeners for card and image and text buttons
        imagesButton.addActionListener(_ -> {
            CardLayout cl = (CardLayout) contentCards.getLayout();
            cl.show(contentCards, "Images");
            imagesButton.setEnabled(false);
            mapButton.setEnabled(true);
        });

        mapButton.addActionListener(_ -> {
            CardLayout cl = (CardLayout) contentCards.getLayout();
            cl.show(contentCards, "Map");
            imagesButton.setEnabled(true);
            mapButton.setEnabled(false);
        });

        add(imagesButton);
        add(mapButton);
    }
}
