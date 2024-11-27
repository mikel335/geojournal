package interface_adapter.viewEntry;

import entity.Entry;

public class ViewEntryState {
    private Entry entry;
    private String viewEntryError;

    public Entry getEntry(){
        return entry;
    }

    public void setEntry(Entry entry) {
        this.entry = entry;
    }

    public String getViewEntryError() {
        return viewEntryError;
    }

    public void setViewEntryError(String viewEntryError) {
        this.viewEntryError = viewEntryError;
    }
}
