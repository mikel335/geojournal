package interface_adapter.addImage;

import use_case.addImage.EditImagesInputBoundary;
import use_case.addImage.AddImageInputData;
import use_case.addImage.DeleteImageInputData;

public class EditImagesController {
    private final EditImagesInputBoundary addImageInteractor;

    public EditImagesController(EditImagesInputBoundary addImageInteractor) {
        this.addImageInteractor = addImageInteractor;
    }

    public void addImage(String imagePath) {
        final AddImageInputData addImageInputData = new AddImageInputData(imagePath);
        this.addImageInteractor.addImage(addImageInputData);
    }

    public void deleteImage(Integer id) {
        final DeleteImageInputData deleteImageInputData = new DeleteImageInputData(id);
        this.addImageInteractor.deleteImage(deleteImageInputData);
    }
}
