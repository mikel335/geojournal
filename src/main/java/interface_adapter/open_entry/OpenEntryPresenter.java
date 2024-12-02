package interface_adapter.open_entry;

import interface_adapter.ViewManagerModel;
import interface_adapter.viewEntry.ViewEntryState;
import interface_adapter.viewEntry.ViewEntryViewModel;
import use_case.open_entry.OpenEntryOutputBoundary;
import use_case.open_entry.OpenEntryOutputData;

public class OpenEntryPresenter implements OpenEntryOutputBoundary {

    private final ViewEntryViewModel viewEntryModel;
    private final ViewManagerModel viewManagerModel;
    
    public OpenEntryPresenter(ViewEntryViewModel viewEntryModel, ViewManagerModel viewManagerModel) {
        this.viewEntryModel = viewEntryModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(OpenEntryOutputData data) {
        final ViewEntryState viewState = this.viewEntryModel.getState();

        viewState.setTitle(data.title());
        viewState.setDescription(data.desc());
        viewState.setLatitude(data.latitude());
        viewState.setLongitude(data.longitude());
        viewState.setImagePaths(data.imagePath());

        viewEntryModel.setState(viewState);
        viewEntryModel.firePropertyChanged();

        viewManagerModel.setState(viewEntryModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        // TODO add error message to state
    }
}
