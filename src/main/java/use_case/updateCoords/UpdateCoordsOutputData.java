package use_case.updateCoords;

public class UpdateCoordsOutputData {
    private final double latitude;
    private final double longitude;

    public UpdateCoordsOutputData(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}

