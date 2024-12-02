package use_case.editImages;

import java.util.Map;

/**
 * The output is the same regardless of whether the user is deleting or adding
 */
public record EditImagesOutputData(Map<Integer, String> imagePaths) {
}
