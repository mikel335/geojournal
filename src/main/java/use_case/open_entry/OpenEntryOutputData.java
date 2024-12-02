package use_case.open_entry;

import java.util.Map;

public record OpenEntryOutputData(int id, String title, String desc, double longitude, double latitude, String date,
                                  Map<Integer, String> imagePath) {

}
