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
     * Gets the ID of the current entry.
     * @return The id of the current entry
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the title of the current entry.
     * @return The title of the current entry
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the current entry.
     * @param title The new title of the current entry
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the description of the current entry.
     * @return The description of the current entry
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the current entry.
     * @param description The new description of the current entry
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Adds image path to current entry
     * @param path The image path of the newly added image
     */
    public void addImagePath(String path) {
        imagePaths.put(newImgId, path);
        newImgId = newImgId + 1;
    }

    /**
     * Removes the image from the current entry.
     * @param id The id of the image of the current entry to be removed
     */
    public void removeImagePath(Integer id) {
        imagePaths.remove(id);
    }

    /**
     * Gets the image paths of the current entry.
     * @return A map whose keys are the image IDs and values are the corresponding image paths of the current entry
     */
    public Map<Integer, String> getImagePaths() {
        return imagePaths;
    }

    /**
     * Gets the longitude of the current entry.
     * @return The longitude of the current entry
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Sets the longitude of the current entry.
     * @param longitude The latitude of the current entry
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * Gets the latitude of the current entry.
     * @return The latitude of the current entry
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Sets the latitude of the current entry.
     * @param latitude The latitude of the current entry
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * Gets the date of the current entry.
     * @return The date of the current entry
     */
    public String getDate() { return date; }

    public void setDate(String date) {
        this.date = date;
    }


    /**
     * Gets the newest image id of the current entry.
     * @return The newest image id of the current entry
     */
    public int getNewImgId() {
        return newImgId;
    }
}