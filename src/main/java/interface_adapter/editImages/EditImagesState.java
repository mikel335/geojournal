package interface_adapter.editImages;

import java.util.Map;

public class EditImagesState {
    private Map<Integer, String> imagePaths;
    private String editImageError;

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
     * Gets the error message for editing images
     * @return The error message for editing images
     */
    public String getEditImageError() {
        return editImageError;
    }

    /**
     * Sets the error message for editing images
     * @param editImageError The desired error message for editing images
     */
    public void setEditImageError(String editImageError) {
        this.editImageError = editImageError;
    }

}
