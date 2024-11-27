package interface_adapter.viewEntry;

import interface_adapter.ViewManagerModel;
import interface_adapter.editImages.EditImagesState;
import interface_adapter.editImages.EditImagesViewModel;
import interface_adapter.updateCoords.UpdateCoordsState;
import interface_adapter.updateCoords.UpdateCoordsViewModel;
import interface_adapter.updateText.UpdateTextState;
import interface_adapter.updateText.UpdateTextViewModel;
import use_case.viewEntry.ViewEntryOutputBoundary;
import use_case.viewEntry.ViewEntryOutputData;

public class ViewEntryPresenter implements ViewEntryOutputBoundary {

    // TODO figure out a way to end up on the correct tab
    // TODO get rid of error on success
    private final ViewEntryViewModel viewEntryView;

    // All potential edit screens
    private final EditImagesViewModel editImagesView;
    private final UpdateCoordsViewModel updateCoordsView;
    private final UpdateTextViewModel updateTextView;

    // View manager
    private final ViewManagerModel viewManagerModel;


    public ViewEntryPresenter(ViewEntryViewModel viewEntryView,
                              EditImagesViewModel editImagesView,
                              UpdateCoordsViewModel updateCoordsView,
                              UpdateTextViewModel updateTextViewModel,
                              ViewManagerModel viewManagerModel) {
        this.viewEntryView = viewEntryView;
        this.editImagesView = editImagesView;
        this.updateCoordsView = updateCoordsView;
        this.updateTextView = updateTextViewModel;
        this.viewManagerModel = viewManagerModel;
    }


    @Override
    public void prepareEditImagesView(ViewEntryOutputData outputData) {
        // Create a new edit images state
        final EditImagesState newEditImagesState = this.editImagesView.getState();
        newEditImagesState.setImagePaths(outputData.getImagePaths());

        // Update the state
        this.editImagesView.setState(newEditImagesState);
        this.editImagesView.firePropertyChanged();

        // Send the view manager to the new page
        this.viewManagerModel.setState(this.editImagesView.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareUpdateCoordsView(ViewEntryOutputData outputData) {
        // Create a new update coords state
        final UpdateCoordsState newUpdateCoordsState = this.updateCoordsView.getState();
        newUpdateCoordsState.setLatitude(outputData.getLatitude());
        newUpdateCoordsState.setLongitude(outputData.getLongitude());

        // Update the state
        this.updateCoordsView.setState(newUpdateCoordsState);
        this.updateCoordsView.firePropertyChanged();

        // Send the view manager to the new page
        this.viewManagerModel.setState(this.updateCoordsView.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareEditTextView(ViewEntryOutputData outputData) {
        // Create a new edit text state
        final UpdateTextState newUpdateTextState = this.updateTextView.getState();
        newUpdateTextState.setTitle(outputData.getTitle());
        newUpdateTextState.setDescription(outputData.getDescription());

        // Update the state
        this.updateTextView.setState(newUpdateTextState);
        this.updateTextView.firePropertyChanged();

        // Send the view manager to the new page
        this.viewManagerModel.setState(this.updateTextView.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }


    // On failure, stay on the view entry view and display an error
    @Override
    public void prepareFailView(String errorMessage) {
        final ViewEntryState newViewEntryState = this.viewEntryView.getState();
        newViewEntryState.setViewEntryError(errorMessage);

        // Update the state
        this.viewEntryView.setState(newViewEntryState);
        this.viewEntryView.firePropertyChanged();
    }

}
