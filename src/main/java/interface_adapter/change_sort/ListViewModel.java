package interface_adapter.change_sort;

import interface_adapter.ViewModel;

public class ListViewModel extends ViewModel<ListState>{

    public ListViewModel() {
        super("listView");
        setState(new ListState());
    }
}