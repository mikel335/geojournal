package use_case.createEntry;

import java.util.Map;

public record CreateEntryOutputData (Map<Integer, String> imagePaths, double latitude, double longitude, String title,
                                     String description) {

}