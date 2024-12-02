package entity;

/**
 * Factory for creating EntryList objects.
 */
public class EntryListFactory {

    /**
     * Crates an EntryList with the given sort method
     * @param sortMethod The method of sorting, 0 for new to old, 1 for old to new
     * @return and EntryList with the desired sort method
     */
    public EntryList create(int sortMethod) {
        return new EntryList(sortMethod);
    }
}