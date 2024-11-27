package interface_adapter.updateText;

import interface_adapter.ViewModel;

public class UpdateTextViewModel extends ViewModel<UpdateTextState> {

    public UpdateTextViewModel() {
        super("viewEntry");
        setState(new UpdateTextState());
    }
}
