package use_case.viewEntry;

public interface ViewEntryInputBoundary {
    /**
     * Go to edit images view
     * @param viewEntryInputData entry information for the next page
     */
    void editImages(ViewEntryInputData viewEntryInputData);

    /**
     * Go to update coordinate view
     * @param viewEntryInputData entry information for the next page
     */
    void updateCoords(ViewEntryInputData viewEntryInputData);

    /**
     * Go to edit text view
     * @param viewEntryInputData entry information for the next page
     */
    void editText(ViewEntryInputData viewEntryInputData);
}
