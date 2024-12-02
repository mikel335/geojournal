package use_case.open_entry;

/**
 * The input data for the open entry use case.
 */
public class OpenEntryInputData {
    private final int id;

    public OpenEntryInputData(int id) {
        this.id = id;
    }

    int getID() {
        return id;
    }
}
