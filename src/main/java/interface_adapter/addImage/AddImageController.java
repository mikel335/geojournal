package interface_adapter.addImage;

import use_case.addImage.EditImagesInputBoundary;
import use_case.addImage.AddImageInputData;
import use_case.addImage.DeleteImageInputData;

public class AddImageController {
    private final EditImagesInputBoundary addImageInteractor;

    public AddImageController(EditImagesInputBoundary addImageInteractor) {
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
