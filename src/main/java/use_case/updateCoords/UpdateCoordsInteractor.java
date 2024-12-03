package use_case.updateCoords;

import entity.Entry;

public class UpdateCoordsInteractor implements UpdateCoordsInputBoundary {

    private final UpdateCoordsDataAccessInterface coordsDataAccess;
    private final UpdateCoordsOutputBoundary updateCoordsPresenter;

    public UpdateCoordsInteractor(UpdateCoordsDataAccessInterface coordsDataAccess,
                                  UpdateCoordsOutputBoundary updateCoordsPresenter) {
        this.coordsDataAccess = coordsDataAccess;
        this.updateCoordsPresenter = updateCoordsPresenter;
    }

    @Override
    public void execute(UpdateCoordsInputData updateCoordsInputData) {
        double latitude;
        try {
            latitude = Double.parseDouble(updateCoordsInputData.latitude());
        } catch (Exception _) {
            updateCoordsPresenter.prepareFailView("This is not a valid latitude value: " + updateCoordsInputData.latitude());
            return;
        }

        double longitude;
        try {
            longitude = Double.parseDouble(updateCoordsInputData.longitude());
        } catch (Exception _) {
            updateCoordsPresenter.prepareFailView("This is not a valid longitude value: " + updateCoordsInputData.longitude());
            return;
        }

        if (90 >= Math.abs(latitude) && 180 >= Math.abs(longitude)) {
            coordsDataAccess.setCoordinates(latitude, longitude);

            Entry updatedEntry = coordsDataAccess.getCurrentEntry();
            final UpdateCoordsOutputData outputData = new UpdateCoordsOutputData(
                    updatedEntry.getLatitude(),
                    updatedEntry.getLongitude());

            updateCoordsPresenter.prepareSuccessView(outputData);
        }
        else {
            updateCoordsPresenter.prepareFailView("(Latitude: " + latitude + ", Longitude: " + longitude + ") is not a valid set of coordinates.");
        }
    }
}
