package use_case.addImage;

public class EditImagesInteractor implements EditImagesInputBoundary {

    private final EditImagesDataAccessInterface addImageDataAccess;
    private final EditImagesOutputBoundary addImagePresenter;

    public EditImagesInteractor(EditImagesDataAccessInterface addImageDataAccess,
                                EditImagesOutputBoundary addImagePresenter) {
        this.addImageDataAccess = addImageDataAccess;
        this.addImagePresenter = addImagePresenter;
    }

    @Override
    public void addImage(AddImageInputData addImageInputData) {
        final String imagePath = addImageInputData.getImagePath();

        try {
            // Attempt to add image to persistent storage
            addImageDataAccess.addImage(imagePath);

            // Get the new list of images and display them
            EditImagesOutputData editImagesOutputData = new EditImagesOutputData(
                    addImageDataAccess.getImagePaths()
            );
            addImagePresenter.prepareSuccessView(editImagesOutputData);

        } catch (Exception e) {
            addImagePresenter.prepareFailView(e.getMessage());
        }
    }

    @Override
    public void deleteImage(DeleteImageInputData deleteImageInputData) {
        final Integer id = deleteImageInputData.getId();

        try {
            // Attempt to delete image from persistent storage
            addImageDataAccess.deleteImage(id);

            // Get new images and display them
            EditImagesOutputData editImagesOutputData = new EditImagesOutputData(
                    addImageDataAccess.getImagePaths()
            );
            addImagePresenter.prepareSuccessView(editImagesOutputData);

        }
        catch (Exception e) {
            addImagePresenter.prepareFailView(e.getMessage());
        }
    }
}
