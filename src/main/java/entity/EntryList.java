package entity;

import java.util.ArrayList;

/**
 * The representation of the entry list in the program
 */
public class EntryList {
    private final int sortMethod; // 0 = new->old, 1 = old->new
    private ArrayList<String>[] entries = new ArrayList[3]; // Stores titles first, then date

    public EntryList(int sortMethod) { this.sortMethod = sortMethod; }

    public int getSortMethod() {
        return sortMethod;
    }

    public ArrayList<String>[] getEntries() {
        return entries;
    }

    public void setEntries(ArrayList<String>[] entries) {
        this.entries = entries;
    }
}