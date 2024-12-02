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
     * Gets all the current entries
     */
    Map<Integer, Entry> getEntryList();

}
