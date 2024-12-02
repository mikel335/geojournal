package interface_adapter.editImages;

import interface_adapter.ViewManagerModel;
import interface_adapter.viewEntry.ViewEntryState;
import interface_adapter.viewEntry.ViewEntryViewModel;
import use_case.editImages.EditImagesOutputBoundary;
import use_case.editImages.EditImagesOutputData;

import javax.swing.text.View;

public class EditImagesPresenter implements EditImagesOutputBoundary {

    private final EditImagesViewModel editImagesViewModel;
    private final ViewEntryViewModel viewEntryViewModel;
    private final ViewManagerModel viewManagerModel;

    public EditImagesPresenter(EditImagesViewModel editImagesViewModel,
                               ViewEntryViewModel viewEntryViewModel,
                                ViewManagerModel viewManagerModel) {
        this.editImagesViewModel = editImagesViewModel;
        this.viewEntryViewModel = viewEntryViewModel;
        this.viewManagerModel = viewManagerModel;
    }
    
    // Return to edit images screen on success
    @Override
    public void prepareSuccessView(EditImagesOutputData outputData) {
        // Create new state with new images
        final EditImagesState newEditImageState = editImagesViewModel.getState();
        newEditImageState.setImagePaths(outputData.imagePaths());

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

    @Override
    public void prepareDoneEditingView(EditImagesOutputData outputData) {
        final ViewEntryState newViewEntryState = viewEntryViewModel.getState();
        newViewEntryState.setImagePaths(outputData.imagePaths());

        // Add the updated images to the state
        this.viewEntryViewModel.setState(newViewEntryState);
        this.viewEntryViewModel.firePropertyChanged();

        // Show the view entry page
        this.viewManagerModel.setState(this.viewEntryViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
