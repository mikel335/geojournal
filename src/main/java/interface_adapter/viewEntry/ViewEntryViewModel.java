package interface_adapter.viewEntry;

import interface_adapter.ViewModel;

public class ViewEntryViewModel extends ViewModel<ViewEntryState> {

    public ViewEntryViewModel() {
        super("viewEntry");
        setState(new ViewEntryState());
    }
}
