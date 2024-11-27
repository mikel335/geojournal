package use_case.editImages;

public interface EditImagesOutputBoundary {

    /**
     * Prepares the success view for the add image use case.
     * @param outputData the output data
     */
    public void prepareSuccessView(EditImagesOutputData outputData);


    /**
     * Prepares the fail view for the update coords use case.
     * @param errorMessage the explanation of the failure
     */
    public void prepareFailView(String errorMessage);
}
