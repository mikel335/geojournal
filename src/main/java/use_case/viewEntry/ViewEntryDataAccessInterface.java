package use_case.viewEntry;

import entity.Entry;

public interface ViewEntryDataAccessInterface {
    /**
     * Fetches all data for an entry from persistent storage
     * @param entryId ID of entry to get data for
     */
    Entry getEntryData(Integer entryId);
}
