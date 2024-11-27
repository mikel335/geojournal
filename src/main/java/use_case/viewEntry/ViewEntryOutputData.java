package use_case.viewEntry;

import entity.Entry;

// TODO figure out if it would be useful to provide methods to supply data for each edit screen

public class ViewEntryOutputData {
    private final Entry entry;
    public ViewEntryOutputData(Entry entry) {
        this.entry = entry;
    }

    public Entry getEntry() {
        return entry;
    }
}

