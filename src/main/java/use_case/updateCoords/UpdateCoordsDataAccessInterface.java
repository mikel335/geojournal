package use_case.updateCoords;

public interface UpdateCoordsDataAccessInterface {

    /**
     * Sets the long/lat coordinates for the map on a journal entry
     * @param latitude Latitude value
     * @param longitude Longitude value
     */
    void setCoords(double latitude, double longitude);

    /**
     * Gets the current value of the longitude from persistent storage
     * @return The latitude
     */
    double getLatitude();

    /**
     * Gets the current value of the latitude from persistent storage
     * @return The longitude
     */
    double getLongitude();
}
