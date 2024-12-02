package use_case.open_entry;

import java.util.Map;

public class OpenEntryOutputData {

    private final int id;
    private final String title;
    private final String desc;
    private final double longitude;
    private final double latitude;
    private final String date;
    private final Map<Integer, String> imagePath;


    public OpenEntryOutputData(int id,
                               String title,
                               String desc,
                               double longitude,
                               double latitude,
                               String date,
                               Map<Integer, String> imagePath) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.longitude = longitude;
        this.latitude = latitude;
        this.date = date;
        this.imagePath = imagePath;
    }
}
