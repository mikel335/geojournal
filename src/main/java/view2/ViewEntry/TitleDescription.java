package view2.ViewEntry;

import javax.swing.*;
import java.awt.*;

public class TitleDescription extends JPanel {
    JLabel titleLabel;
    JLabel descriptionLabel;

    public TitleDescription() {
        this.titleLabel = new JLabel();
        this.descriptionLabel = new JLabel();

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(300, 600));

        add(titleLabel, BorderLayout.NORTH);
        add(descriptionLabel, BorderLayout.SOUTH);
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
