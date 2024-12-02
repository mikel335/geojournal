package use_case.updateCoords;

public interface UpdateCoordsOutputBoundary {

    /**
     * Prepares the success view for the update coords use case.
     * @param outputData the output data
     */
    void prepareSuccessView(UpdateCoordsOutputData outputData);

    /**
     * Prepares the failure view for the update coords use case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}
