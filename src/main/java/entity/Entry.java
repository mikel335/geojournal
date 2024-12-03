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
     * Gets the ID of this entry.
     * @return The id of this entry
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the title of this entry.
     * @return The title of this entry
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of this entry.
     * @param title The new title of this entry
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the description of this entry.
     * @return The description of this entry
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of this entry.
     * @param description The new description of this entry
     */
    public void setDescription(String description) {
        this.description = description;
    }


    /**
     * Gets the image path of this entry.
     * @param path The image path of the newly added image
     * @return The new image id of this entry
     */
    public void addImagePath(String path) {
        imagePaths.put(newImgId, path);
        newImgId++;
    }

    /**
     * Removes the image path of the given image id of this entry.
     * @param id The id of the image of this entry to be removed
     */
    public void removeImagePath(Integer id) {
        imagePaths.remove(id);
    }

    /**
     * Gets the image paths of this entry.
     * @return A map whose keys are the image IDs and values are the corresponding image paths of this entry
     */
    public Map<Integer, String> getImagePaths() {
        return imagePaths;
    }

    /**
     * Gets the longitude of this entry.
     * @return The longitude of this entry
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Sets the longitude of this entry.
     * @param longitude The latitude of this entry
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * Gets the latitude of this entry.
     * @return The latitude of this entry
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Sets the latitude of this entry.
     * @param latitude The latitude of this entry
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * Gets the date of this entry.
     * @return The date of this entry
     */
    public String getDate() { return date; }

    /**
     * Gets the newest image id of this entry.
     * @return The newest image id of this entry
     */
    public int getNewImgId() {
        return newImgId;
    }
}