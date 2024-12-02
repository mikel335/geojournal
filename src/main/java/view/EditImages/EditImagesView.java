package view.EditImages;

import interface_adapter.editImages.EditImagesController;
import interface_adapter.editImages.EditImagesViewModel;
import view.Components.ImageDisplayPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;


public class EditImagesView extends JPanel implements ActionListener, PropertyChangeListener {

    private final EditImagesViewModel editImagesViewModel;
    private EditImagesController editImagesController;

    private final JButton uploadButton;
    private final JButton doneButton;

    private final ImageDisplayPanel imageDisplayPanel;

    public EditImagesView(EditImagesViewModel editImagesViewModel) {
        this.editImagesViewModel = editImagesViewModel;
        this.editImagesViewModel.addPropertyChangeListener(this);
        this.imageDisplayPanel = new ImageDisplayPanel(editImagesViewModel.getState().getImagePaths(), false);

        // Button panel that gives us add image and done buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());

        uploadButton = new JButton("Upload Image");
        uploadButton.setForeground(Color.GREEN.darker());
        uploadButton.addActionListener(this);
        buttonPanel.add(uploadButton);

        doneButton = new JButton("Done");
        doneButton.setForeground(Color.BLUE);
        doneButton.addActionListener(this);
        buttonPanel.add(doneButton);

        imageDisplayPanel.setPreferredSize(new Dimension(1200, 700));

        setLayout(new BorderLayout());
        add(imageDisplayPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == uploadButton) {
            uploadImage();
        } else if (e.getSource() == doneButton) {
            this.editImagesController.returnToEntryView();
        }
    }

    private void uploadImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
                "Image files", "jpg", "png", "jpeg", "gif"));

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            this.editImagesController.addImage(selectedFile.getAbsolutePath());
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.imageDisplayPanel.updateImagePaths(editImagesViewModel.getState().getImagePaths());
    }

    public void setEditImagesController(EditImagesController editImagesController) {
        this.editImagesController = editImagesController;
        this.imageDisplayPanel.updateImagePanelControllers(editImagesController);
    }
}