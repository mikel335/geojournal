package interface_adapter.updateCoords;

public class UpdateCoordsState {
    private double latitude = 0;
    private double longitude = 0;
    private String updateCoordsError;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getUpdateCoordsError() {
        return updateCoordsError;
    }

    public void setUpdateCoordsError(String updateCoordsError) {
        this.updateCoordsError = updateCoordsError;
    }
}
