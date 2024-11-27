package entity;
import java.util.ArrayList;

public class Entry {

    final private int id;

    // Title and description
    private String title;
    private String description;

    // Image Data
    private ArrayList<String> imagePaths = new ArrayList<>();

    // Map Data
    private double longitude;
    private double latitude;

    public Entry(int id,
                 String title,
                 String description,
                 ArrayList<String> imagePaths,
                 double longitude,
                 double latitude) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.imagePaths = imagePaths;
        this.longitude = longitude;
        this.latitude = latitude;
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

    public void addImagePath(String imagePath) {}
    public void removeImagePath(String imagePath) {}

    public ArrayList<String> getImagePaths() {
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