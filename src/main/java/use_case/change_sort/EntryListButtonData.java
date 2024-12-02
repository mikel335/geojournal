package use_case.change_sort;

import entity.Entry;

import java.util.Date;

public class EntryListButtonData {
    private final int id;
    private final String title;
    private final String date;

    public EntryListButtonData(Entry entry) {
        this.id = entry.getId();
        this.title = entry.getTitle();
        this.date = entry.getDate();
    }

    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getDate() {
        return date;
    }
}
