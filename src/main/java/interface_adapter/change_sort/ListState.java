package interface_adapter.change_sort;

import use_case.change_sort.EntryListButtonData;
import use_case.change_sort.SortMethod;

import java.util.ArrayList;

/**
 * The state information representing the list view/
 */
public class ListState {
    private ArrayList<EntryListButtonData> entryList;
    private String errorMessage;

    /**
     * Gets the list of buttons' data corresponding to the entries in the entry list view
     * @return The ArrayList of EntryListButtonData of the entry list view's entry buttons
     */
    public ArrayList<EntryListButtonData> getEntryList() {
        return entryList;
    }

    /**
     * Sets the list of buttons' data corresponding to the entries in the entry list view
     * @param entryList The desired ArrayList of EntryListButtonData of the entry list view's entry buttons
     */
    public void setEntryList(ArrayList<EntryListButtonData> entryList) {
        this.entryList = entryList;
    }

    /**
     * Gets the error message
     * @return The error message
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Sets the error message
     * @param errorMessage The new, desired error message
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
