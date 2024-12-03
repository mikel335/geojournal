package view.Components;

import interface_adapter.editImages.EditImagesController;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.*;

public class ImageDisplayPanel extends JPanel {
    private final Map<Integer, ImagePanel> imagePanelById;
    private final GridLayout gridLayout;
    private final JPanel grid;
    private EditImagesController editImagesController;

    public ImageDisplayPanel(Map<Integer, String> imagePaths) {
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

    /**
     * Updates the image paths based on the given map.
     * @param imagePaths A map that maps each image's ID to their image path
     */
    public void updateImagePaths(Map<Integer, String> imagePaths) {
        // Note all IDs are unique to an image even after it has been removed

        if (imagePaths == null) {
            return;
        }

        Set<Integer> currentStateImages = imagePaths.keySet();
        Set<Integer> currentlyDisplayedImages = this.imagePanelById.keySet();

        for (Integer newImageId : currentStateImages) {
            // If newImageId is not currently displayed, add it to the view
            if (!currentlyDisplayedImages.contains(newImageId)) {

                ImagePanel imagePanel = new ImagePanel(imagePaths.get(newImageId), newImageId);
                imagePanel.setEditImagesController(editImagesController);
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
        grid.revalidate();
        grid.repaint();
    }

    /**
     * Updates the image panel controllers.
     * @param controller The edit image use case's controller
     */
    public void updateImagePanelControllers(EditImagesController controller) {
        this.editImagesController = controller;
        for (ImagePanel imagePanel : this.imagePanelById.values()) {
            imagePanel.setEditImagesController(controller);
        }
    }
}
