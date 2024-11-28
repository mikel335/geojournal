package entity;

/**
 * Factory for creating EntryList objects.
 */
public class EntryListFactory {
    public EntryList create(int sortMethod) {
        return new EntryList(sortMethod);
    }
}