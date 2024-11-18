package interface_adapter.change_sort;

import use_case.change_sort.ChangeSortOutputBoundary;
import use_case.change_sort.ChangeSortOutputData;

/**
 * The presenter for the change sort use case.
 */
public class ChangeSortPresenter implements ChangeSortOutputBoundary{
    private final EntryListViewModel entryListViewModel;

    public ChangeSortPresenter(EntryListViewModel entryListViewModel) {
        this.entryListViewModel = entryListViewModel;
    }

    @Override
    public void prepareSuccessView(ChangeSortOutputData outputData) {
        // TODO: Update the list order based on sort method
    }

    @Override
    public void prepareFailView(String message) {
        // Empty because this use case cannot fail.
    }
}