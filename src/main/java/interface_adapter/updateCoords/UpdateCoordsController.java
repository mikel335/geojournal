package interface_adapter.updateCoords;

import use_case.updateCoords.UpdateCoordsInputBoundary;
import use_case.updateCoords.UpdateCoordsInputData;

public class UpdateCoordsController {
    private final UpdateCoordsInputBoundary updateCoordsInteractor;

    public UpdateCoordsController(UpdateCoordsInputBoundary updateCoordsInteractor) {
        this.updateCoordsInteractor = updateCoordsInteractor;

    }

    public void execute(double latitude, double longitude) {
        final UpdateCoordsInputData updateCoordsInputData = new UpdateCoordsInputData(latitude, longitude);
        updateCoordsInteractor.execute(updateCoordsInputData);
    }
}
