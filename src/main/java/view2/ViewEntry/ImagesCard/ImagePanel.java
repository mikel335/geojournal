package view2.ViewEntry.ImagesCard;

import javax.swing.*;
import java.awt.*;

public class ImagePanel extends JPanel {
    private static final int IMAGE_WIDTH = 350;
    private static final int IMAGE_HEIGHT = 300;

    public ImagePanel(String path) {
        ImageIcon imageIcon = new ImageIcon(path);

        Image image = imageIcon.getImage();
        Image resizedImage = image.getScaledInstance(IMAGE_WIDTH, IMAGE_HEIGHT, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        JLabel imageLabel = new JLabel(resizedIcon);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel imagePanel = new JPanel(new BorderLayout());
        imagePanel.add(imageLabel, BorderLayout.CENTER);
    }
}
