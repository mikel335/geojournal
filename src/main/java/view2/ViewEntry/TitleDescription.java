package view2.ViewEntry;

import javax.swing.*;
import java.awt.*;

public class TitleDescription extends JPanel {
    private String title;
    private String description;

    public TitleDescription(String title, String description) {
        this.title = title;
        this.description = description;

        setLayout(new BorderLayout());

        setPreferredSize(new Dimension(300, 600));
        JLabel titleLabel = new JLabel(title);
        JLabel descriptionLabel = new JLabel(description);

        add(titleLabel, BorderLayout.NORTH);
        add(descriptionLabel, BorderLayout.SOUTH);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
