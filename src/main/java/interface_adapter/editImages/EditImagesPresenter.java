package interface_adapter.editImages;

import interface_adapter.ViewManagerModel;
import use_case.editImages.EditImagesOutputBoundary;
import use_case.editImages.EditImagesOutputData;

import java.util.Map;

public class EditImagesPresenter implements EditImagesOutputBoundary {

    private final EditImagesViewModel editImagesViewModel;

    public EditImagesPresenter(EditImagesViewModel editImagesViewModel) {
        this.editImagesViewModel = editImagesViewModel;
    }

    // TODO Add in a "save" view that returns to viewEntry
    // TODO figure out a way to end up on the correct tab
    // TODO get rid of error on success

    // Return to edit images screen on success
    @Override
    public void prepareSuccessView(EditImagesOutputData outputData) {
        // Create new state with new images
        final EditImagesState newEditImageState = editImagesViewModel.getState();
        newEditImageState.setImagePaths(outputData.getImagePaths());

        // Update the view model with the new state
        this.editImagesViewModel.setState(newEditImageState);
        this.editImagesViewModel.firePropertyChanged();

    }

    @Override
    public void prepareFailView(String errorMessage) {

        // Create new state with error message
        final EditImagesState newEditImageState = editImagesViewModel.getState();
        newEditImageState.setEditImageError(errorMessage);

        // Update the view model with the new state
        this.editImagesViewModel.setState(newEditImageState);
        this.editImagesViewModel.firePropertyChanged();

    }
}
