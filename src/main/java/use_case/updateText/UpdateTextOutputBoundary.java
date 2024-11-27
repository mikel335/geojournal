package use_case.updateText;


public interface UpdateTextOutputBoundary {
    /**
     * Prepares the success view for the update text use case.
     * @param outputData the output data
     */
    void prepareSuccessView(UpdateTextOutputData outputData);

    /**
     * Prepares the failure view for the update text use case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}
