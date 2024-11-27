package interface_adapter.updateText;

import interface_adapter.ViewModel;

public class ViewEntryViewModel extends ViewModel<UpdateTextState> {

    public ViewEntryViewModel() {
        super("viewEntry");
        setState(new UpdateTextState());
    }
}
