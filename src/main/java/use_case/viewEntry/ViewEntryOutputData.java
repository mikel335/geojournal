package use_case.viewEntry;

import entity.Entry;

import java.util.Map;

// TODO figure out if it would be useful to provide methods to supply data for each edit screen

public class ViewEntryOutputData {
    private final Entry entry;
    public ViewEntryOutputData(Entry entry) {
        this.entry = entry;
    }

    public Entry getEntry() {
        return entry;
    }

    public Map<Integer, String> getImagePaths() {
        return entry.getImagePaths();
    }

    public double getLatitude() {
        return entry.getLatitude();
    }

    public double getLongitude() {
        return entry.getLongitude();
    }

    public String getTitle() {
        return entry.getTitle();
    }

    public String getDescription() {
        return entry.getDescription();
    }
}

