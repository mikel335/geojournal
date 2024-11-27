package use_case.editImages;

import java.util.Map;

/**
 * The output is the same regardless of whether the user is deleting or adding
 */
public class EditImagesOutputData {
    private final Map<Integer, String> imagePaths;

    public EditImagesOutputData(Map<Integer, String> imagePaths) {
        this.imagePaths = imagePaths;
    }

    public Map<Integer, String> getImagePaths() {
        return imagePaths;
    }
}
