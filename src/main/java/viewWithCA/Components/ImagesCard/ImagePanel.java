package viewWithCA.Components.ImagesCard;

import javax.swing.*;
import java.awt.*;

public class ImagePanel extends JLabel {
    public ImagePanel(String path) {
        ImageIcon imageIcon = new ImageIcon(path);

        Image image = imageIcon.getImage();
        Image resizedImage = image.getScaledInstance(350, 350, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        setIcon(resizedIcon);
        setHorizontalAlignment(SwingConstants.CENTER);
    }
}
