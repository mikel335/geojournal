package use_case.editImages;

public interface EditImagesInputBoundary {

    /**
     * Adds an image to the entry
     * @param addImageInputData new image data
     */
    void addImage(AddImageInputData addImageInputData);

    /**
     * Deletes an image from the entry
     * @param deleteImageInputData new image data
     */
    void deleteImage(DeleteImageInputData deleteImageInputData);

    /**
     * Returns to view entry screen when editing is done
     */
    void returnToEntryView();
}
