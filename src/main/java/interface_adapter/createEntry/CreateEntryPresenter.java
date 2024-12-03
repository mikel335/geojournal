package interface_adapter.createEntry;

import interface_adapter.ViewManagerModel;
import interface_adapter.change_sort.ListState;
import interface_adapter.change_sort.ListViewModel;
import interface_adapter.viewEntry.ViewEntryState;
import interface_adapter.viewEntry.ViewEntryViewModel;
import use_case.createEntry.CreateEntryOutputBoundary;
import use_case.createEntry.CreateEntryOutputData;

public class CreateEntryPresenter implements CreateEntryOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final ViewEntryViewModel viewEntryViewModel;
    private final ListViewModel entryListViewModel;

    public CreateEntryPresenter(ViewManagerModel viewManagerModel,
                                ViewEntryViewModel viewEntryViewModel,
                                ListViewModel entryListViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.viewEntryViewModel = viewEntryViewModel;
        this.entryListViewModel = entryListViewModel;
    }
    @Override
    public void prepareSuccessView(CreateEntryOutputData output) {
        final ViewEntryState newState = this.viewEntryViewModel.getState();
        newState.setTitle(output.title());
        newState.setDescription(output.description());
        newState.setLongitude(output.longitude());
        newState.setLatitude(output.latitude());
        newState.setImagePaths(output.imagePaths());

        this.viewEntryViewModel.setState(newState);
        this.viewEntryViewModel.firePropertyChanged();

        this.viewManagerModel.setState(this.viewEntryViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        final ListState newState = this.entryListViewModel.getState();
        newState.setErrorMessage(errorMessage);

        this.entryListViewModel.setState(newState);
        this.entryListViewModel.firePropertyChanged();

        newState.setErrorMessage(null);
        this.entryListViewModel.setState(newState);
    }
}
