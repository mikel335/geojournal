package interface_adapter.change_sort;

import interface_adapter.ViewManagerModel;
import use_case.change_sort.ChangeSortOutputBoundary;
import use_case.change_sort.ChangeSortOutputData;

/**
 * The presenter for the change sort use case.
 */
public class ChangeSortPresenter implements ChangeSortOutputBoundary{
    private final ViewManagerModel viewManagerModel;
    private final ListViewModel entryListViewModel;

    public ChangeSortPresenter(ViewManagerModel viewManagerModel, ListViewModel entryListViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.entryListViewModel = entryListViewModel;
    }

    @Override
    public void prepareSuccessView(ChangeSortOutputData outputData) {
        final ListState listState = entryListViewModel.getState();

        listState.setEntryList(outputData.orderedEntries());

        this.entryListViewModel.setState(listState);
        this.entryListViewModel.firePropertyChanged();

        this.viewManagerModel.setState(entryListViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String message) {
        final ListState listState = entryListViewModel.getState();
        listState.setErrorMessage(message);

        this.entryListViewModel.setState(listState);
        this.entryListViewModel.firePropertyChanged();

        listState.setErrorMessage(null);
        this.entryListViewModel.setState(listState);
    }
}