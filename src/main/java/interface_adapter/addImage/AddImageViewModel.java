package interface_adapter.addImage;

import interface_adapter.ViewModel;

public class AddImageViewModel extends ViewModel<AddImageState> {

    public AddImageViewModel() {
        super("addImage");
        setState(new AddImageState());
    }
}
