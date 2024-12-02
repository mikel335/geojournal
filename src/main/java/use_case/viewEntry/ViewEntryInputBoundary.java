package use_case.viewEntry;

public interface ViewEntryInputBoundary {
    /**
     * Go to edit images view
     */
    void editImages();

    /**
     * Go to update coordinate view
     */
    void updateCoords();

    /**
     * Go to edit text view
     */
    void editText();

    /**
     * Go back to all entries
     */
    void returnToList();

    /**
     * Go to view entry view
     */
    void viewEntry();
}
