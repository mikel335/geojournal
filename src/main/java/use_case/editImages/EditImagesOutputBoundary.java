package use_case.editImages;

public interface EditImagesOutputBoundary {

    /**
     * Prepares the success view for the add image use case.
     * @param outputData the output data
     */
    public void prepareSuccessView(EditImagesOutputData outputData);


    /**
     * Prepares the fail view for the edit images use case.
     * @param errorMessage the explanation of the failure
     */
    public void prepareFailView(String errorMessage);


    /**
     * Prepares the viewEntry screen for when editing is done
     * @param outputData new image data
     */
    public void prepareDoneEditingView(EditImagesOutputData outputData);
}
