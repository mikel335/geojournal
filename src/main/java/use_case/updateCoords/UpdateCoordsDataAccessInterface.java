package use_case.updateCoords;

import entity.Entry;

public interface UpdateCoordsDataAccessInterface {

    /**
     * Sets the long/lat coordinates for the map on a journal entry
     * @param latitude Latitude value
     * @param longitude Longitude value
     */
    void setCoordinates(double latitude, double longitude);

    Entry getCurrentEntry();

}
