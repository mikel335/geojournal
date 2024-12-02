package entity;

import java.util.Map;

public class EntryFactory {

    public Entry createEntry(int id,
                             String title,
                             String desc,
                             Map<Integer, String> imagePath,
                             double longitude,
                             double latitude,
                             String date) {
        return new Entry(id, title, desc, imagePath, longitude, latitude, date);
    }
}