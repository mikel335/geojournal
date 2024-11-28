package use_case.change_sort;

/**
 * The output boundary for the change sort use case.
 */
public interface ChangeSortOutputBoundary {
    /**
     * Prepares the success view for the change sort use case.
     * @param outputData the output data
     */
    void prepareSuccessView(ChangeSortOutputData outputData);

    /**
     * Prepares the failure view for the change sort use case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}