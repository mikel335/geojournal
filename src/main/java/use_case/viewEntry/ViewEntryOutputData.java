package use_case.viewEntry;

import java.util.Map;

public record ViewEntryOutputData(Map<Integer, String> imagePaths, double latitude, double longitude, String title,
                                  String description) {

}

