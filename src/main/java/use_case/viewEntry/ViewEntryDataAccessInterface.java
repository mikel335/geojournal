package use_case.viewEntry;

import entity.Entry;

import java.util.Map;

public interface ViewEntryDataAccessInterface {

    /**
     * Gets the current entry
     * @return the current entry
     */
    Entry getCurrentEntry();
    
    /**
     * Gets a map that maps IDs to their respective entry.
     * @return A map that maps IDs to their respective entry
     */
    Map<Integer, Entry> getEntryList();

}
