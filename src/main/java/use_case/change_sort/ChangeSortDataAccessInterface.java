package use_case.change_sort;

import entity.Entry;

import java.util.Map;

/**
 * The interface of the data access object for the change sort use case.
 */
public interface ChangeSortDataAccessInterface {

    /**
     * Gets the map that maps the id to each entry.
     * @return A map that maps IDs to their respective entries.
     */
    Map<Integer, Entry> getEntryList();
}