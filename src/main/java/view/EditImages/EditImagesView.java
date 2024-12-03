package view.EditImages;

import interface_adapter.editImages.EditImagesController;
import interface_adapter.editImages.EditImagesState;
import interface_adapter.editImages.EditImagesViewModel;
import view.Components.Colors;
import view.Components.ImageDisplayPanel;
import view.Components.StyledButton;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
        setBackground(Colors.lightBlue);
        this.editImagesViewModel = editImagesViewModel;
        this.editImagesViewModel.addPropertyChangeListener(this);
        this.imageDisplayPanel = new ImageDisplayPanel(editImagesViewModel.getState().getImagePaths(), true);

        // Button panel that gives us add image and done buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(Colors.lightBlue);

        uploadButton = new StyledButton("Upload Image");
        uploadButton.setForeground(Color.WHITE);
        uploadButton.addActionListener(this);
        buttonPanel.add(uploadButton);

        doneButton = new StyledButton("Done");
        doneButton.setForeground(Color.GREEN);
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
        if (evt.getNewValue() instanceof EditImagesState newState) {
            if (newState.getEditImageError() != null) {
                JOptionPane.showMessageDialog(null, newState.getEditImageError(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        this.imageDisplayPanel.updateImagePaths(editImagesViewModel.getState().getImagePaths());
    }

    /**
     * Sets the controller for editing images.
     * @param editImagesController The desired controller for editing images
     */
    public void setEditImagesController(EditImagesController editImagesController) {
        this.editImagesController = editImagesController;
        this.imageDisplayPanel.updateImagePanelControllers(editImagesController);
    }
}
