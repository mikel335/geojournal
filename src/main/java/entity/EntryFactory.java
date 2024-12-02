package entity;

import java.util.Map;

public class EntryFactory {

    /**
     * Creates an entry with the given parameters.
     * @param id ID of the entry
     * @param title Title of the entry
     * @param desc Description of the entry
     * @param imagePath Image path of the entry
     * @param longitude Longitude of the entry
     * @param latitude Latitude of the entry
     * @param date Date of the entry
     * @return An entry with the given parameters as its attributes
     */
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