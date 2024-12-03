package view.Components;

import interface_adapter.editImages.EditImagesController;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Image;

public class ImagePanel extends JPanel {
    private boolean editMode = false;
    private EditImagesController editImagesController;

    public ImagePanel(String path, int id) {
        ImageIcon imageIcon = new ImageIcon(path);

        Image image = imageIcon.getImage();
        Image resizedImage = image.getScaledInstance(350, 350, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        if (editMode) {
            JButton deleteSingleImageButton = new JButton("Delete");
            add(deleteSingleImageButton, BorderLayout.SOUTH);
            deleteSingleImageButton.addActionListener(_ -> editImagesController.deleteImage(id));
        }

        setLayout(new BorderLayout());
        add(new JLabel(resizedIcon), BorderLayout.CENTER);
    }

    /**
     * Sets whether or not the user is currently in edit mode.
     * @param editMode Whether or not the user is current in edit mode.
     */
    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }

    /**
     * Sets the controller for editing images.
     * @param editImagesController The controller for the edit images use case
     */
    public void setEditImagesController(EditImagesController editImagesController) {
        this.editImagesController = editImagesController;
    }
}
