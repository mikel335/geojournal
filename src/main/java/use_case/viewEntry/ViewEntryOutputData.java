package use_case.viewEntry;

import java.util.Map;

public class ViewEntryOutputData {

    private final Map<Integer, String> imagePaths;
    private final double latitude;
    private final double longitude;
    private final String title;
    private final String description;

    public ViewEntryOutputData(Map<Integer, String> imagePaths,
                               double latitude,
                               double longitude,
                               String title,
                               String description) {
        this.imagePaths = imagePaths;
        this.latitude = latitude;
        this.longitude = longitude;
        this.title = title;
        this.description = description;
    }


    public Map<Integer, String> getImagePaths() {
        return imagePaths;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}

