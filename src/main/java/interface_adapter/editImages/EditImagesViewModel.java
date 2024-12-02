package interface_adapter.editImages;

import interface_adapter.ViewModel;

public class EditImagesViewModel extends ViewModel<EditImagesState> {

    public EditImagesViewModel() {
        super("editImages");
        setState(new EditImagesState());
    }
}
