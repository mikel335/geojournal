package use_case.change_sort;

import java.util.ArrayList;

import entity.EntryList;

/**
 * The interface of the data access object for the change sort use case.
 */
public interface ChangeSortDataAccessInterface {
    /**
     * Updates the system to record the entry list's sort method.
     * @param entryList the entrylist whose sort method is to be updated
     */
    void changeSortAndUpdate(EntryList entryList);
}