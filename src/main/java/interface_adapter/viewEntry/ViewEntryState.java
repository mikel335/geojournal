package interface_adapter.viewEntry;

import entity.Entry;

public class ViewEntryState {
    private Entry entry;

    public Entry getEntry(){
        return entry;
    }

    public void setEntry(Entry entry) {
        this.entry = entry;
    }
}
