package view.ViewEntry;

import view.Components.Colors;

import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.Font;

import java.awt.Dimension;
import java.awt.BorderLayout;

public class TitleDescription extends JPanel {
    JTextArea titleLabel;
    JTextArea descriptionLabel;

    public TitleDescription(String title, String description) {
        setBackground(Colors.lightBlue);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(300, 600));

        this.titleLabel = new JTextArea(title);
        this.titleLabel.setFont(new Font("Sans Serif", Font.BOLD, 30));
        this.titleLabel.setBorder(BorderFactory.createEmptyBorder(0,0,20,0));
        this.titleLabel.setCursor(null);
        this.titleLabel.setOpaque(false);
        this.titleLabel.setFocusable(false);
        this.titleLabel.setWrapStyleWord(true);
        this.titleLabel.setLineWrap(true);


        this.descriptionLabel = new JTextArea(description);
        this.descriptionLabel.setEditable(false);
        this.descriptionLabel.setCursor(null);
        this.descriptionLabel.setOpaque(false);
        this.descriptionLabel.setFocusable(false);
        this.descriptionLabel.setWrapStyleWord(true);
        this.descriptionLabel.setLineWrap(true);

        this.descriptionLabel.setFont(new Font("Sans Serif", Font.PLAIN, 15));


        add(titleLabel, BorderLayout.NORTH);
        add(descriptionLabel, BorderLayout.CENTER);
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
