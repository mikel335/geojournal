package interface_adapter.open_entry;

import interface_adapter.ViewManagerModel;
import interface_adapter.viewEntry.ViewEntryState;
import interface_adapter.viewEntry.ViewEntryViewModel;
import use_case.open_entry.OpenEntryOutputBoundary;
import use_case.open_entry.OpenEntryOutputData;

public class OpenEntryPresenter implements OpenEntryOutputBoundary {

    private final ViewEntryViewModel viewModel;
    private final ViewManagerModel viewManagerModel;
    public OpenEntryPresenter(ViewEntryViewModel viewModel, ViewManagerModel viewManagerModel) {
        this.viewModel = viewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(OpenEntryOutputData data) {
        final ViewEntryState viewState = this.viewModel.getState();
        viewState.setTitle(data.title());
        viewState.setDescription(data.desc());
        viewState.setLatitude(data.latitude());
        viewState.setLongitude(data.longitude());
        viewState.setImagePaths(data.imagePath());

        viewModel.setState(viewState);
        viewModel.firePropertyChanged();

        viewManagerModel.setState(viewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {

    }
}
