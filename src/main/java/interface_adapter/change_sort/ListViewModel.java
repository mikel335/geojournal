package interface_adapter.change_sort;

import data_access.EntryDataAccess;
import interface_adapter.ViewModel;

public class ListViewModel extends ViewModel<ListState>{

    public static final String ASCENDING_BUTTON_LABEL = "Oldest to Newest";
    public static final String DESCENDING_BUTTON_LABEL = "Newest to Oldest";

    public ListViewModel(EntryDataAccess dataAccess) {
        super("list view");
        // TODO: Take in arraylists for ListState and parameters and input them
        setState(new ListState());
    }
}