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
        final double latitude = updateCoordsInputData.latitude();
        final double longitude = updateCoordsInputData.longitude();

        if (90 >= Math.abs(latitude) && 180 >= Math.abs(longitude)) {
            coordsDataAccess.setCoordinates(latitude, longitude);

            Entry updatedEntry = coordsDataAccess.getCurrentEntry();
            final UpdateCoordsOutputData outputData = new UpdateCoordsOutputData(
                    updatedEntry.getLatitude(),
                    updatedEntry.getLongitude());

            updateCoordsPresenter.prepareSuccessView(outputData);
        }
        else {
            updateCoordsPresenter.prepareFailView("(Latitude: " + latitude + ", Longitude" + longitude + ") is not a valid set of coordinates.");
        }
    }
}
