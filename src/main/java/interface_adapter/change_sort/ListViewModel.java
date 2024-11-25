package interface_adapter.change_sort;

import interface_adapter.ViewModel;

public class ListViewModel extends ViewModel<ListState>{

    public static final String ASCENDING_BUTTON_LABEL = "Oldest to Newest";
    public static final String DESCENDING_BUTTON_LABEL = "Newest to Oldest";

    public ListViewModel() {
        super("list view");
        setState(new ListState());
    }
}