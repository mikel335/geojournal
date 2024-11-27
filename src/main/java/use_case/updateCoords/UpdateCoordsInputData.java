package use_case.updateCoords;

/**
 * Input data for the update coordinate use case
 */
public class UpdateCoordsInputData {
    final private double longitude;
    final private double latitude;

    public UpdateCoordsInputData(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

}
