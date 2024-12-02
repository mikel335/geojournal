package use_case.change_sort;

import entity.Entry;
import entity.EntryList;
import entity.EntryListFactory;

import java.util.ArrayList;
import java.util.Map;

/**
 * The change sort interactor.
 */
public class ChangeSortInteractor implements ChangeSortInputBoundary{
    private final ChangeSortDataAccessInterface dataAccessObject;
    private final ChangeSortOutputBoundary presenter;
    private final EntryListFactory entryListFactory;

    public ChangeSortInteractor(ChangeSortDataAccessInterface changeSortDataAccessInterface,
                                ChangeSortOutputBoundary changeSortOutputBoundary,
                                EntryListFactory entryListFactory) {
        this.dataAccessObject = changeSortDataAccessInterface;
        this.presenter = changeSortOutputBoundary;
        this.entryListFactory = entryListFactory;
    }

    @Override
    public void execute(ChangeSortInputData changeSortInputData) {

        try {
            ArrayList<EntryListButtonData> sortedEntries = new ArrayList<>();

            for (Entry entry : dataAccessObject.getEntryList().values()) {
                sortedEntries.add(new EntryListButtonData(entry));
            }
            sortedEntries.sort(new EntryListButtonDataComparitor(changeSortInputData.getSortMethod()));

            ChangeSortOutputData output = new ChangeSortOutputData(changeSortInputData.getSortMethod(), sortedEntries);
            presenter.prepareSuccessView(output);

        } catch(Exception e) {
            presenter.prepareFailView("There was an issue accessing the entries" + e.getMessage());
        }
    }
}
