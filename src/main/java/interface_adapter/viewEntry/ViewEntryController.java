package interface_adapter.viewEntry;

import use_case.viewEntry.ViewEntryInputBoundary;


public class ViewEntryController {
    private final ViewEntryInputBoundary viewEntryInteractor;

    public ViewEntryController(ViewEntryInputBoundary viewEntryInteractor) {
        this.viewEntryInteractor = viewEntryInteractor;
    }

    public void editImages() {
        viewEntryInteractor.editImages();
    }

    public void editText() {
        viewEntryInteractor.editText();
    }

    public void updateCoords() {
        viewEntryInteractor.updateCoords();
    }

    public void viewEntry() { viewEntryInteractor.viewEntry(); }

}
