package interface_adapter.open_entry;

import interface_adapter.ViewManagerModel;
import interface_adapter.viewEntry.ViewEntryState;
import interface_adapter.viewEntry.ViewEntryViewModel;
import use_case.open_entry.OpenEntryOutputBoundary;
import use_case.open_entry.OpenEntryOutputData;
import view.MainEntryView;
import view.ViewManager;

public class OpenEntryPresenter implements OpenEntryOutputBoundary {

    private final ViewEntryViewModel viewModel;
    private final ViewManagerModel viewManagerModel;
    public OpenEntryPresenter(ViewEntryViewModel viewModel, ViewManagerModel viewManagerModel) {
        this.viewModel = viewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(OpenEntryOutputData data) {
        final ViewEntryState state = viewModel.getState();
        // TODO: change the state with the given data
        viewModel.setState(state);
        viewModel.firePropertyChanged();

        viewManagerModel.setState(viewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {

    }
}
