package use_case.change_sort;

import entity.EntryList;
import entity.EntryListFactory;

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
        final EntryList entryList = entryListFactory.create(changeSortInputData.getSortMethod());
        dataAccessObject.changeSortAndUpdate(entryList);

        final ChangeSortOutputData changeSortOutputData = new ChangeSortOutputData(entryList.getSortMethod(),
                false,
                entryList.getEntries());
        presenter.prepareSuccessView(changeSortOutputData);
    }
}