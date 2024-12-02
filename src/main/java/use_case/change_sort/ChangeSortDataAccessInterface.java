package use_case.change_sort;

import entity.Entry;

import java.util.Map;

/**
 * The interface of the data access object for the change sort use case.
 */
public interface ChangeSortDataAccessInterface {
    /**
     * Updates the system to record the entry list's sort method.
     */
    Map<Integer, Entry> getEntryList();
}