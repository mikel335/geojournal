package use_case.viewEntry;

public interface ViewEntryOutputBoundary {

    /**
     * Prepares the view to add an image
     * @param outputData the output data
     */
    void prepareAddImageView(ViewEntryOutputData outputData);


    /**
     * Prepares the view to update coords
     * @param outputData the output data
     */
    void prepareUpdateCoordsView(ViewEntryOutputData outputData);

    /**
     * Prepares the failure view
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}
