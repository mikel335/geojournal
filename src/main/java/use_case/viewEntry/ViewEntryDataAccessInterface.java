package use_case.viewEntry;

public interface ViewEntryDataAccessInterface {

    /**
     * Sets the long/lat coordinates for the map on a journal entry
     * @param latitude Latitude value
     * @param longitude Longitude value
     */
    void setCoords(double latitude, double longitude);
}
