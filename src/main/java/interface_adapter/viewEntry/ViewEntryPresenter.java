package interface_adapter.viewEntry;

import interface_adapter.ViewManagerModel;
import interface_adapter.change_sort.ListState;
import interface_adapter.change_sort.ListViewModel;
import interface_adapter.editImages.EditImagesState;
import interface_adapter.editImages.EditImagesViewModel;
import interface_adapter.updateCoords.UpdateCoordsState;
import interface_adapter.updateCoords.UpdateCoordsViewModel;
import interface_adapter.updateText.UpdateTextState;
import interface_adapter.updateText.UpdateTextViewModel;
import use_case.change_sort.ChangeSortOutputData;
import use_case.viewEntry.ViewEntryOutputBoundary;
import use_case.viewEntry.ViewEntryOutputData;

public class ViewEntryPresenter implements ViewEntryOutputBoundary {

    private final ViewEntryViewModel viewEntryView;
    private final ListViewModel entryListViewModel;

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
                              ViewManagerModel viewManagerModel,
                              ListViewModel entryListViewModel) {
        this.viewEntryView = viewEntryView;
        this.editImagesView = editImagesView;
        this.updateCoordsView = updateCoordsView;
        this.updateTextView = updateTextViewModel;
        this.viewManagerModel = viewManagerModel;
        this.entryListViewModel = entryListViewModel;
    }


    @Override
    public void prepareEditImagesView(ViewEntryOutputData outputData) {
        // Create a new edit images state
        final EditImagesState newEditImagesState = this.editImagesView.getState();
        newEditImagesState.setImagePaths(outputData.imagePaths());

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
        newUpdateCoordsState.setLatitude(outputData.latitude());
        newUpdateCoordsState.setLongitude(outputData.longitude());

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
        newUpdateTextState.setTitle(outputData.title());
        newUpdateTextState.setDescription(outputData.description());

        // Update the state
        this.updateTextView.setState(newUpdateTextState);
        this.updateTextView.firePropertyChanged();

        // Send the view manager to the new page
        this.viewManagerModel.setState(this.updateTextView.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareViewEntryView(ViewEntryOutputData outputData) {
        // Create a new view entry state
        final ViewEntryState newViewEntryState = this.viewEntryView.getState();

        newViewEntryState.setTitle(outputData.title());
        newViewEntryState.setDescription(outputData.description());
        newViewEntryState.setLatitude(outputData.latitude());
        newViewEntryState.setLongitude(outputData.longitude());
        newViewEntryState.setImagePaths(outputData.imagePaths());

        this.viewEntryView.setState(newViewEntryState);
        this.viewEntryView.firePropertyChanged();

        this.viewManagerModel.setState(this.viewEntryView.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareEntryList(ChangeSortOutputData outputData) {
        final ListState listState = entryListViewModel.getState();

        listState.setEntryList(outputData.orderedEntries());
        listState.setSortMethod(outputData.sortMethod());

        this.entryListViewModel.setState(listState);
        this.entryListViewModel.firePropertyChanged();

        this.viewManagerModel.setState(entryListViewModel.getViewName());
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
