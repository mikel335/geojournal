package use_case.open_entry;

/**
 * The output boundary for the open entry use case.
 */
public interface OpenEntryOutputBoundary {
    /**
     * Prepares the success view for the open entry use case.
     * @param outputData the output data
     */
    void prepareSuccessView(OpenEntryOutputData outputData);

    /**
     * Prepares the failure view for the open entry use case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}
