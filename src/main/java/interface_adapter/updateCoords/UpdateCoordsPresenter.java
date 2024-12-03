package interface_adapter.updateCoords;

import interface_adapter.ViewManagerModel;

import interface_adapter.viewEntry.ViewEntryState;
import interface_adapter.viewEntry.ViewEntryViewModel;
import use_case.updateCoords.UpdateCoordsOutputBoundary;
import use_case.updateCoords.UpdateCoordsOutputData;

public class UpdateCoordsPresenter implements UpdateCoordsOutputBoundary {

    private final UpdateCoordsViewModel updateCoordsView;
    private final ViewEntryViewModel entryView;
    private final ViewManagerModel viewManagerModel;


    public UpdateCoordsPresenter(UpdateCoordsViewModel updateCoordsView,
                                 ViewEntryViewModel entryView,
                                 ViewManagerModel viewManagerModel) {
        this.updateCoordsView = updateCoordsView;
        this.entryView = entryView;
        this.viewManagerModel = viewManagerModel;
    }

    // On success switch back to entry view
    @Override
    public void prepareSuccessView(UpdateCoordsOutputData outputData) {
        // Create new entry view state with new coordinates
        final ViewEntryState newViewEntryState = entryView.getState();
        newViewEntryState.setLatitude(outputData.latitude());
        newViewEntryState.setLongitude(outputData.longitude());

        // Update the state
        this.entryView.setState(newViewEntryState);
        this.entryView.firePropertyChanged();

        // Update the view manager model with the new view
        this.viewManagerModel.setState(this.entryView.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    // On failure, stay on the edit coords view and display an error
    @Override
    public void prepareFailView(String errorMessage) {
        // Create new entry view state with new coordinates
        final UpdateCoordsState newUpdateCoordsState = updateCoordsView.getState();
        newUpdateCoordsState.setUpdateCoordsError(errorMessage);

        // Update the state
        this.updateCoordsView.setState(newUpdateCoordsState);
        this.updateCoordsView.firePropertyChanged();

        newUpdateCoordsState.setUpdateCoordsError(null);
        this.updateCoordsView.setState(newUpdateCoordsState);
    }

}
