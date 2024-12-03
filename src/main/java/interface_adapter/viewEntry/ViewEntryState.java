package interface_adapter.viewEntry;

import java.util.Map;

public class ViewEntryState {

    // Title and description
    private String title;
    private String description;

    // Image Data
    private Map<Integer, String> imagePaths;

    // Map Data
    private double longitude;
    private double latitude;

    private String viewEntryError;

    /**
     * Gets the title of the entry.
     * @return The title of the entry
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the entry
     * @param title The new title of the entry to set to
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the description of the entry.
     * @return The description of the entry
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the entry
     * @param description The new description of the entry to set to
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the mapping of image IDs to the respective image's path
     * @return A map mapping image IDs to their respective image's path
     */
    public Map<Integer, String> getImagePaths() {
        return imagePaths;
    }

    /**
     * Sets the mapping of image IDs to the respective image's path
     * @param imagePaths The desired mapping of image IDs to their respective image's path
     */
    public void setImagePaths(Map<Integer, String> imagePaths) {
        this.imagePaths = imagePaths;
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
     * Gets the view entry error message.
     * @return The view entry error message
     */
    public String getViewEntryError() {
        return viewEntryError;
    }

    /**
     * Sets the view entry error message.
     * @param viewEntryError The new view entry error message
     */
    public void setViewEntryError(String viewEntryError) {
        this.viewEntryError = viewEntryError;
    }
}
