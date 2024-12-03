package interface_adapter.viewEntry;

import use_case.viewEntry.ViewEntryInputBoundary;


public class ViewEntryController {
    private final ViewEntryInputBoundary viewEntryInteractor;

    public ViewEntryController(ViewEntryInputBoundary viewEntryInteractor) {
        this.viewEntryInteractor = viewEntryInteractor;
    }

    /**
     * Calls the appropriate method in the interactor to edit images in the currently open entry.
     */
    public void editImages() {
        viewEntryInteractor.editImages();
    }

    /**
     * Calls the appropriate method in the interactor to edit text in the currently open entry.
     */
    public void editText() {
        viewEntryInteractor.editText();
    }

    /**
     * Calls the appropriate method in the interactor to edit coordinates in the currently open entry.
     */
    public void updateCoords() {
        viewEntryInteractor.updateCoords();
    }

    /**
     * Calls the appropriate method in the interactor to return to entry view in the currently open entry.
     */
    public void viewEntry() { viewEntryInteractor.viewEntry(); }

    /**
     * Calls the appropriate method in the interactor to return to the entry list in the currently open entry.
     */
    public void returnToHome() { viewEntryInteractor.returnToList(); }
}
