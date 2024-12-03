package interface_adapter.updateCoords;

public class UpdateCoordsState {
    private double latitude = 0;
    private double longitude = 0;
    private String updateCoordsError;

    /**
     * Gets the current latitude
     * @return The current latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Sets the latitude
     * @param latitude The new latitude to set to
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * Gets the current longitude
     * @return The current longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Sets the current longitude
     * @param longitude The new longitude to set to
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * Gets the error message for updating coordinates
     * @return The error message for updating coordinates
     */
    public String getUpdateCoordsError() {
        return updateCoordsError;
    }

    /**
     * Sets the error message for updating coordinates
     * @param updateCoordsError The new error message for updating coordinates
     */
    public void setUpdateCoordsError(String updateCoordsError) {
        this.updateCoordsError = updateCoordsError;
    }
}
