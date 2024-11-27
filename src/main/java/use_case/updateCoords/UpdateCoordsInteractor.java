package use_case.updateCoords;

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
            coordsDataAccess.setCoords(latitude, longitude);

            final UpdateCoordsOutputData outputData = new UpdateCoordsOutputData(
                    coordsDataAccess.getLatitude(),
                    coordsDataAccess.getLongitude());

            updateCoordsPresenter.prepareSuccessView(outputData);
        }
        else {
            updateCoordsPresenter.prepareFailView("(Latitude: " + latitude + ", Longitude" + longitude + ") is not a valid set of coordinates.");
        }
    }
}
