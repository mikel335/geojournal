package view.Components;

import interface_adapter.editImages.EditImagesController;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Image;

public class ImagePanel extends JPanel {
    private final int id;

    public ImagePanel(String path, int id) {
        setLayout(new BorderLayout());
        this.id = id;

        ImageIcon imageIcon = new ImageIcon(path);

        Image image = imageIcon.getImage();
        Image resizedImage = image.getScaledInstance(350, 350, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        add(new JLabel(resizedIcon), BorderLayout.CENTER);
    }

    /**
     * Sets the controller for editing images.
     * @param editImagesController The controller for the edit images use case
     */
    public void setEditImagesController(EditImagesController editImagesController) {
        if (editImagesController != null) {
            JButton deleteSingleImageButton = new StyledButton("Delete");
            add(deleteSingleImageButton, BorderLayout.SOUTH);
            deleteSingleImageButton.addActionListener(_ -> editImagesController.deleteImage(id));
        }
    }
}
