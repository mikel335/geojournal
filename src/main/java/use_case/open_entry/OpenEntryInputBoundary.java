package use_case.open_entry;

/**
 * The open entry use case.
 */
public interface OpenEntryInputBoundary {
    /**
     * Execute the change sort use case.
     * @param openEntryInputData the input data for this use case.
     */
    void execute(OpenEntryInputData openEntryInputData);
}
