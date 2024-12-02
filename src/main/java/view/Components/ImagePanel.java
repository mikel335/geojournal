package view.Components;

import interface_adapter.editImages.EditImagesController;

import javax.swing.*;
import java.awt.*;

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

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }

    public void setEditImagesController(EditImagesController editImagesController) {
        this.editImagesController = editImagesController;
    }
}
