package interface_adapter.addImage;

import java.util.Map;

public class EditImagesState {
    private Map<Integer, String> imagePaths;
    private String editImageError;

    public Map<Integer, String> getImagePaths() {
        return imagePaths;
    }

    public void setImagePaths(Map<Integer, String> imagePaths) {
        this.imagePaths = imagePaths;
    }

    public String getEditImageError() {
        return editImageError;
    }

    public void setEditImageError(String editImageError) {
        this.editImageError = editImageError;
    }

}
