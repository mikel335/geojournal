package interface_adapter.updateText;

import interface_adapter.ViewManagerModel;
import interface_adapter.viewEntry.ViewEntryState;
import interface_adapter.viewEntry.ViewEntryViewModel;
import use_case.updateText.UpdateTextOutputBoundary;
import use_case.updateText.UpdateTextOutputData;

public class UpdateTextPresenter implements UpdateTextOutputBoundary {

    private final UpdateTextViewModel updateTextView;
    private final ViewEntryViewModel entryView;
    private final ViewManagerModel viewManagerModel;

    public UpdateTextPresenter(UpdateTextViewModel updateTextView, ViewEntryViewModel entryView,
                               ViewManagerModel viewManagerModel) {
        this.updateTextView = updateTextView;
        this.entryView = entryView;
        this.viewManagerModel = viewManagerModel;
    }

    // TODO figure out a way to end up on the correct tab
    // TODO Remove error message on success
    // Return to the view entry page on success
    @Override
    public void prepareSuccessView(UpdateTextOutputData outputData) {
        // Create new entry view state with new coordinates
        final ViewEntryState newViewEntryState = entryView.getState();
        newViewEntryState.setTitle(outputData.title());
        newViewEntryState.setDescription(outputData.description());

        // Update the state
        this.entryView.setState(newViewEntryState);
        this.entryView.firePropertyChanged();

        // Update the view manager model with the new view
        this.viewManagerModel.setState(this.entryView.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    // Stay on the same page and add error message on failure
    @Override
    public void prepareFailView(String errorMessage) {

        // Create new state with error message
        final UpdateTextState newUpdateTextState = updateTextView.getState();
        newUpdateTextState.setUpdateTextError(errorMessage);

        // Update the state
        this.updateTextView.setState(newUpdateTextState);
        this.updateTextView.firePropertyChanged();
    }
}
