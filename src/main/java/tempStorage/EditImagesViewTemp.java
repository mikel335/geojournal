package tempStorage;

import interface_adapter.editImages.EditImagesController;
import interface_adapter.editImages.EditImagesViewModel;
import viewWithCA.Components.ImagePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class EditImagesViewTemp extends JPanel implements ActionListener, PropertyChangeListener {

    private final EditImagesViewModel editImagesViewModel;
    private EditImagesController editImagesController;

    private final JButton uploadButton;
    private final JButton doneButton;


    private final Map<Integer, ImagePanel> imagePanelById;
    private final GridLayout gridLayout;
    private final JPanel grid;

    public EditImagesViewTemp(EditImagesViewModel editImagesViewModel) {
        this.editImagesViewModel = editImagesViewModel;

        setLayout(new BorderLayout());
        gridLayout = new GridLayout();
        grid = new JPanel(gridLayout);

        this.imagePanelById = new HashMap<>();
        this.updateImagePaths(editImagesViewModel.getState().getImagePaths());

        JScrollPane scrollPane = new JScrollPane(grid);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // Button panel that gives us add image and done buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        uploadButton = new JButton("Upload Image");
        uploadButton.setForeground(Color.GREEN.darker());
        uploadButton.addActionListener(this);
        buttonPanel.add(uploadButton);

        doneButton = new JButton("Done");
        doneButton.setForeground(Color.BLUE);
        doneButton.addActionListener(this);
        buttonPanel.add(doneButton);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == uploadButton) {
            uploadImage();
        } else if (e.getSource() == doneButton) {
            this.editImagesController.returnToEntryView();
        } else {
            for (Integer id : imagePanelById.keySet()) {
                Object source = e.getSource();
                if (source == imagePanelById.get(id)) {
                    editImagesController.deleteImage(id);
                }
            }
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

    // Note all IDs are unique to an image even after it has been removed
    public void updateImagePaths(Map<Integer, String> imagePaths) {
        if (imagePaths == null) {
            return;
        }

        Set<Integer> currentStateImages = imagePaths.keySet();
        Set<Integer> currentlyDisplayedImages = this.imagePanelById.keySet();

        for (Integer newImageId : currentStateImages) {
            // If newImageId is not currently displayed, add it to the view
            if (!currentlyDisplayedImages.contains(newImageId)) {
                ImagePanel imagePanel = new ImagePanel(imagePaths.get(newImageId), newImageId);
                imagePanel.setEditMode(true);
                grid.add(imagePanel);
                this.imagePanelById.put(newImageId, imagePanel);
            }
        }

        for (Integer oldImageId : currentlyDisplayedImages) {
            // If oldImageId is not in the current state (ie shouldn't be displayed),
            //      it should be removed from the view
            if (!currentStateImages.contains(oldImageId)) {
                grid.remove(this.imagePanelById.get(oldImageId));
                this.imagePanelById.remove(oldImageId);
            }
        }

        // Set up the columns and rows to fit all the images
        int rows = Math.ceilDiv(currentStateImages.size(), 3);
        this.gridLayout.setColumns(3);
        this.gridLayout.setRows(rows);
        this.gridLayout.setHgap(10);
        this.gridLayout.setVgap(10);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.updateImagePaths(editImagesViewModel.getState().getImagePaths());
    }

    public void setEditImagesController(EditImagesController editImagesController) {
        this.editImagesController = editImagesController;
    }
}
