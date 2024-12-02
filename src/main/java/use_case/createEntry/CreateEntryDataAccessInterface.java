package use_case.createEntry;

import entity.Entry;

public interface CreateEntryDataAccessInterface {
    /**
     * Creates a new entry
     * @return The id of the new entry
     */
    Entry createEntry();
}
