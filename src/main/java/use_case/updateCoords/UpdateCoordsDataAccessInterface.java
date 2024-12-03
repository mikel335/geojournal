package use_case.updateCoords;

import entity.Entry;

public interface UpdateCoordsDataAccessInterface {

    /**
     * Sets the long/lat coordinates for the map on a journal entry
     * @param latitude Latitude value
     * @param longitude Longitude value
     */
    void setCoordinates(double latitude, double longitude);

    /**
     * Gets the current entry whose coordinates are being updated.
     * @return The entry whose coordinates are being updated.
     */
    Entry getCurrentEntry();
}
