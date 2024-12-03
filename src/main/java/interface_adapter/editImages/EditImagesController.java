package interface_adapter.editImages;

import use_case.editImages.EditImagesInputBoundary;
import use_case.editImages.AddImageInputData;
import use_case.editImages.DeleteImageInputData;

public class EditImagesController {
    private final EditImagesInputBoundary addImageInteractor;

    public EditImagesController(EditImagesInputBoundary addImageInteractor) {
        this.addImageInteractor = addImageInteractor;
    }

    /**
     * Adds an image using the given image path
     * @param imagePath
     */
    public void addImage(String imagePath) {
        final AddImageInputData addImageInputData = new AddImageInputData(imagePath);
        this.addImageInteractor.addImage(addImageInputData);
    }

    /**
     * Deletes an image with the given image id
     * @param id
     */
    public void deleteImage(Integer id) {
        final DeleteImageInputData deleteImageInputData = new DeleteImageInputData(id);
        this.addImageInteractor.deleteImage(deleteImageInputData);
    }

    /**
     * Runs the required methods to return the application to the entry view.
     */
    public void returnToEntryView() {
        this.addImageInteractor.returnToEntryView();
    }
}
