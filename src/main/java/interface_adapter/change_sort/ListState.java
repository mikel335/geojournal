package interface_adapter.change_sort;

import use_case.change_sort.EntryListButtonData;
import use_case.change_sort.SortMethod;

import java.util.ArrayList;

/**
 * The state information representing the list view/
 */
public class ListState {
    private ArrayList<EntryListButtonData> entryList;
    private SortMethod sortMethod;

    public ArrayList<EntryListButtonData> getEntryList() {
        return entryList;
    }

    public void setEntryList(ArrayList<EntryListButtonData> entryList) {
        this.entryList = entryList;
    }

    public SortMethod getSortMethod() {
        return sortMethod;
    }

    public void setSortMethod(SortMethod sortMethod) {
        this.sortMethod = sortMethod;
    }
}
