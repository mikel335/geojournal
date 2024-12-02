package view.Components;

import interface_adapter.editImages.EditImagesController;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class ImageDisplayPanel extends JPanel {
    private final Map<Integer, ImagePanel> imagePanelById;
    private final GridLayout gridLayout;
    private final JPanel grid;
    private final boolean editMode;

    public ImageDisplayPanel(Map<Integer, String> imagePaths, boolean editMode) {
        this.editMode = editMode;

        setLayout(new BorderLayout());
        gridLayout = new GridLayout();
        grid = new JPanel(gridLayout);

        this.imagePanelById = new HashMap<>();
        this.updateImagePaths(imagePaths);

        JScrollPane scrollPane = new JScrollPane(grid);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane, BorderLayout.CENTER);
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
                imagePanel.setEditMode(editMode);
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

    public void updateImagePanelControllers(EditImagesController controller) {
        for (ImagePanel imagePanel : this.imagePanelById.values()) {
            imagePanel.setEditMode(editMode);
            imagePanel.setEditImagesController(controller);
        }
    }
}
