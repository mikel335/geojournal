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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<Integer, String> getImagePaths() {
        return imagePaths;
    }

    public void setImagePaths(Map<Integer, String> imagePaths) {
        this.imagePaths = imagePaths;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getViewEntryError() {
        return viewEntryError;
    }

    public void setViewEntryError(String viewEntryError) {
        this.viewEntryError = viewEntryError;
    }
}
