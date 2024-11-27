package interface_adapter.addImage;

import java.util.Map;

public class AddImageState {
    private Map<Integer, String> imagePaths;
    private String addImageError;

    public Map<Integer, String> getImagePaths() {
        return imagePaths;
    }

    public void addImagePath(Integer id, String imagePath) {
        imagePaths.put(id, imagePath);
    }

    public void removeImagePath(Integer id) {
        imagePaths.remove(id);
    }

    public String getAddImageError() {
        return addImageError;
    }

    public void setAddImageError(String addImageError) {
        this.addImageError = addImageError;
    }

}
