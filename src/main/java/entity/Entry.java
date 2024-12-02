package entity;
import java.util.Map;
import java.util.HashMap;

public class Entry {
    final private int id;
    private int newImgId = 0;

    // Title and description and date
    private String title;
    private String description;
    private String date;

    // Image Data
    final private Map<Integer, String> imagePaths;

    // Map Data
    private double longitude;
    private double latitude;

    public Entry(int id,
                 String title,
                 String description,
                 Map<Integer, String> imagePaths,
                 double longitude,
                 double latitude,
                 String date) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.description = description;
        this.longitude = longitude;
        this.latitude = latitude;
        this.imagePaths = new HashMap<>(imagePaths);
    }

    /**
     * Gets the ID of the entry
     * @return The ID of the entry
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the title of the entry
     * @return The title of the entry
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the entry
     * @param title The desired title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the description of the entry
     * @return The description of the entry
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the entry
     * @param description The desired description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Adds the image path to this entry
     * @param path The desired image path
     */
    public int addImagePath(String path) {
        imagePaths.put(newImgId, path);
        newImgId++;
        return newImgId;
    }
    /**
     * Removes the image path of this entry.
     */
    public void removeImagePath(Integer id) {
        imagePaths.remove(id);
    }

    /**
     * Gets the image path of the entry
     * @return The image path of the entry
     */
    public Map<Integer, String> getImagePaths() {
        return imagePaths;
    }
    /**
     * Gets the longitude of the entry
     * @return longitude ID of the entry
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Sets the longitude of the entry
     * @param longitude The desired longitude
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * Gets the latitude of the entry
     * @return The latitude of the entry
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Sets the latitude of the entry
     * @param latitude The desired latitude
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * Gets the new image ID of the entry
     * @return The new image ID of the entry
     */
    public int getNewImgId() {
        return newImgId;
    }

    /**
     * Gets the date of the entry
     * @return The date of the entry
     */
    public String getDate() { return date; }
}