package use_case.viewEntry;

public interface ViewEntryOutputBoundary {

    /**
     * Prepares the view to edit the images
     * @param outputData the entry data
     */
    void prepareEditImagesView(ViewEntryOutputData outputData);

    /**
     * Prepares the view to update coords
     * @param outputData the entry data
     */
    void prepareUpdateCoordsView(ViewEntryOutputData outputData);

    /**
     * Prepares the view to update text
     * @param outputData the entry data
     */
    void prepareEditTextView(ViewEntryOutputData outputData);

    /**
     * Prepares the failure view
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}
