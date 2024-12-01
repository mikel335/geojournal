package viewWithCA.ViewEntry;

import javax.swing.*;
import java.awt.*;

public class TitleDescription extends JPanel {
    JLabel titleLabel;
    JLabel descriptionLabel;

    public TitleDescription(String title, String description) {
        this.titleLabel = new JLabel(title);
        this.descriptionLabel = new JLabel(description);

        setLayout(new FlowLayout(FlowLayout.CENTER));
        setPreferredSize(new Dimension(300, 600));

        add(titleLabel);
        add(descriptionLabel);
    }


    public void setTitle(String title) {
        titleLabel.setText(title);
        titleLabel.repaint();
    }

    public void setDescription(String description) {
        descriptionLabel.setText(description);
        descriptionLabel.repaint();
    }
}
