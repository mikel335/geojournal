package use_case.editImages;

import entity.Entry;

public class EditImagesInteractor implements EditImagesInputBoundary {

    private final EditImagesDataAccessInterface editImageDataAccess;
    private final EditImagesOutputBoundary editImagePresenter;

    public EditImagesInteractor(EditImagesDataAccessInterface editImageDataAccess,
                                EditImagesOutputBoundary editImagePresenter) {
        this.editImageDataAccess = editImageDataAccess;
        this.editImagePresenter = editImagePresenter;
    }

    @Override
    public void addImage(AddImageInputData addImageInputData) {
        final String imagePath = addImageInputData.imagePath();

        try {
            // Attempt to add image to persistent storage
            editImageDataAccess.addImageToCurrentEntry(imagePath);

            // Get the new list of images and display them
            Entry updatedEntry = editImageDataAccess.getCurrentEntry();
            EditImagesOutputData editImagesOutputData = new EditImagesOutputData(updatedEntry.getImagePaths());
            editImagePresenter.prepareSuccessView(editImagesOutputData);

        } catch (Exception e) {
            editImagePresenter.prepareFailView(e.getMessage());
        }
    }

    @Override
    public void deleteImage(DeleteImageInputData deleteImageInputData) {
        final Integer id = deleteImageInputData.id();

        try {
            // Attempt to delete image from persistent storage
            editImageDataAccess.deleteImageFromCurrentEntry(id);

            // Get the new list of images and display them
            Entry updatedEntry = editImageDataAccess.getCurrentEntry();
            EditImagesOutputData editImagesOutputData = new EditImagesOutputData(updatedEntry.getImagePaths());
            editImagePresenter.prepareSuccessView(editImagesOutputData);

        }
        catch (Exception e) {
            editImagePresenter.prepareFailView(e.getMessage());
        }
    }

    @Override
    public void returnToEntryView() {
        try {
            EditImagesOutputData editImagesOutputData = new EditImagesOutputData(editImageDataAccess.getCurrentEntry().getImagePaths());
            editImagePresenter.prepareDoneEditingView(editImagesOutputData);
        } catch (Exception e) {
            editImagePresenter.prepareFailView(e.getMessage());
        }
    }
}
