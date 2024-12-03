package use_case.change_sort;

import entity.Entry;

public class EntryListButtonData {
    private final int id;
    private final String title;
    private final String date;

    public EntryListButtonData(Entry entry) {
        this.id = entry.getId();
        this.title = entry.getTitle();
        this.date = entry.getDate();
    }

    /**
     * Gets the ID of the entry associated with this button.
     * @return The ID of the entry associated with this button
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the title of the entry associated with this button.
     * @return The title of the entry associated with this button
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the date of the entry associated with this button.
     * @return The date of the entry associated with this button
     */
    public String getDate() {
        return date;
    }
}
