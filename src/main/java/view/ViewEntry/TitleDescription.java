package view.ViewEntry;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

public class TitleDescription extends JPanel {
    JLabel titleLabel;
    JTextArea descriptionLabel;

    public TitleDescription(String title, String description) {
        // TODO make this look less wonky
        // TODO add scroll bar
        this.titleLabel = new JLabel(title);
        this.titleLabel.setFont(new Font("Sans Serif", Font.BOLD, 30));
        this.titleLabel.setBorder(BorderFactory.createEmptyBorder(0,0,20,0));
        this.titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        this.descriptionLabel = new JTextArea(description);
        this.descriptionLabel.setEditable(false);
        this.descriptionLabel.setCursor(null);
        this.descriptionLabel.setOpaque(false);
        this.descriptionLabel.setFocusable(false);
        this.descriptionLabel.setWrapStyleWord(true);
        this.descriptionLabel.setLineWrap(true);

        this.descriptionLabel.setFont(new Font("Sans Serif", Font.PLAIN, 15));

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(300, 600));

        add(titleLabel);
        add(descriptionLabel);
    }

    /**
     * Sets the title of the entry.
     * @param title The title of the entry to set to
     */
    public void setTitle(String title) {
        titleLabel.setText(title);
        titleLabel.repaint();
    }

    /**
     * Sets the description of the entry.
     * @param description The description of the entry to set to
     */
    public void setDescription(String description) {
        descriptionLabel.setText(description);
        descriptionLabel.repaint();
    }
}
