package view2.ViewEntry.ImagesCard;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class ImagesCard extends JPanel {
    private Map<Integer, String> imagePaths;
    private final Map<Integer, JPanel> imagePanels;

    private final JPanel imagesPanel;

    public ImagesCard(Map<Integer, String> imagePaths) {
        this.imagePaths = imagePaths;
        this.imagePanels = new HashMap<>();

        imagesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JScrollPane scrollPane = new JScrollPane(imagesPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        add(scrollPane);
    }


    // Note all IDs are unique to an image even after it has been removed
    public void updateImagePaths(Map<Integer, String> imagePaths) {
        Set<Integer> newImages = imagePaths.keySet();
        Set<Integer> oldImages = this.imagePaths.keySet();

        // Add all new images not in the current state
        // Create a panel for them as well
        for (Integer newImageId : newImages) {
            if (!oldImages.contains(newImageId)) {
                this.imagePaths.put(newImageId, imagePaths.get(newImageId));
                ImagePanel imagePanel = new ImagePanel(imagePaths.get(newImageId));
                this.imagePanels.put(newImageId, imagePanel);
            }
        }

        // Remove all old images that have been deleted
        for (Integer oldImageId : oldImages) {
            if (!newImages.contains(oldImageId)) {
                this.imagePaths.remove(oldImageId);
                this.imagePanels.remove(oldImageId);
            }
        }
    }
}
