package entity;
import java.util.Map;
import java.util.HashMap;

public class Entry {
    final private int id;

    // Title and description
    private String title;
    private String description;

    // Image Data
    private Map<Integer, String> imagePaths;

    // Map Data
    private double longitude;
    private double latitude;

    public Entry(int id,
                 String title,
                 String description,
                 Map<Integer, String> imagePaths,
                 double longitude,
                 double latitude) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.longitude = longitude;
        this.latitude = latitude;
        this.imagePaths = new HashMap<>(imagePaths);
    }


    public int getId() {
        return id;
    }

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

    public void addImagePath(Integer id, String path) {
        imagePaths.put(id, path);
    }

    public void removeImagePath(Integer id) {
        imagePaths.remove(id);
    }

    public Map<Integer, String> getImagePaths() {
        return imagePaths;
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
}