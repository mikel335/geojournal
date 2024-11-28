package use_case.open_entry;

/**
 * The input data for the open entry use case.
 */
public class OpenEntryInputData {
    private final String title;

    public OpenEntryInputData(String title) {
        this.title = title;
    }

    String getTitle() {
        return title;
    }
}
