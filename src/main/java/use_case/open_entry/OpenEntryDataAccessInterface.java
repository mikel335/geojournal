package use_case.open_entry;

import entity.Entry;

/**
 * The interface of the data access object for the open entry use case.
 */
public interface OpenEntryDataAccessInterface {
    /**
     * Gets information on an entry based on the id.
     * @param id The id of the entry
     */
    Entry getEntry(int id);

    /**
     * Sets the current entry
     * @param id The id of the entry to set as the current entry
     */
    void setCurrentEntry(int id);
}
