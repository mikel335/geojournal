package viewWithCA.ViewEntry.ImagesCard;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class ImagesCard extends JPanel {
    private final Map<Integer, ImagePanel> imagePanelById;
    private final GridLayout gridLayout;
    private final JPanel grid;

    public ImagesCard(Map<Integer, String> imagePaths) {
        setLayout(new BorderLayout());
        gridLayout = new GridLayout(3, 3);
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
                ImagePanel imagePanel = new ImagePanel(imagePaths.get(newImageId));
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
}
