package interface_adapter.updateCoords;

import interface_adapter.ViewModel;

public class UpdateCoordsViewModel extends ViewModel<UpdateCoordsState> {

    public UpdateCoordsViewModel() {
        super("updateCoords");
        setState(new UpdateCoordsState());
    }
}
