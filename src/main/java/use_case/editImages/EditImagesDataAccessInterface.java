package use_case.editImages;

import entity.Entry;

import java.util.Map;

public interface EditImagesDataAccessInterface {

    /**
     * Adds an image to the current entry sourced from the given path.
     * Image will be added at the end of the images.
     * @param imagePath Path to image to be added to the entry
     */
    void addImageToCurrentEntry(String imagePath);

    /**
     * Removes an image from the entry
     * @param id ID of the image to be removed
     */
    void deleteImageFromCurrentEntry(Integer id);
    
    Entry getCurrentEntry();
}
