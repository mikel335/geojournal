package use_case.editImages;

import java.util.Map;

public interface EditImagesDataAccessInterface {

    /**
     * Adds an image to the entry sourced from the given path.
     * Image will be added at the end of the images.
     * @param imagePath Path to image to be added to the entry
     */
    void addImage(String imagePath);

    /**
     * Removes an image from the entry
     * @param id ID of the image to be removed
     */
    void deleteImage(Integer id);

    /**
     * Get all the images for the current page
     * @return Map of all the image paths for this entry by ID
     */
    Map<Integer, String> getImagePaths();

}
