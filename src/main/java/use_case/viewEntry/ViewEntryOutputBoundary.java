package use_case.viewEntry;

import use_case.change_sort.ChangeSortOutputData;

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
     * Prepares the view to view the entry
     * @param outputData the entry data
     */
    void prepareViewEntryView(ViewEntryOutputData outputData);

    /**
     * Prepares the view to go back to entry list
     */
    void prepareEntryList(ChangeSortOutputData outputData);

    /**
     * Prepares the failure view
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}
