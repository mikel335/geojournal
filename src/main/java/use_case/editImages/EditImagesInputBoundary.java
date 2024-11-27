package use_case.editImages;

public interface EditImagesInputBoundary {

    /**
     * Adds an image to the entry
     * @param addImageInputData new image data
     */
    void addImage(AddImageInputData addImageInputData);

    /**
     * Deletes an image from the entry
     * @param deleteImageInputData
     */
    void deleteImage(DeleteImageInputData deleteImageInputData);
}
