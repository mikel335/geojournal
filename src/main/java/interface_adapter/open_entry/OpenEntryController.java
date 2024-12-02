package interface_adapter.open_entry;

import use_case.open_entry.OpenEntryInputBoundary;
import use_case.open_entry.OpenEntryInputData;

/**
 * Controller for the open entry use case.
 */
public class OpenEntryController {
    private final OpenEntryInputBoundary openEntryUseCaseBoundary;

    public OpenEntryController(OpenEntryInputBoundary openEntryUseCaseBoundary) {
        this.openEntryUseCaseBoundary = openEntryUseCaseBoundary;
    }

    /**
     * Executes the open entry use case.
     * @param id the id of the entry to open
     */
    public void execute(int id) {
        final OpenEntryInputData openEntryInputData = new OpenEntryInputData(id);
        openEntryUseCaseBoundary.execute(openEntryInputData);
    }
}
