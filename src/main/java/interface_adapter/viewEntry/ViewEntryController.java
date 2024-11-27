package interface_adapter.viewEntry;

import use_case.viewEntry.ViewEntryInputData;
import use_case.viewEntry.ViewEntryInputBoundary;


public class ViewEntryController {
    private final ViewEntryInputBoundary viewEntryInteractor;

    public ViewEntryController(ViewEntryInputBoundary viewEntryInteractor) {
        this.viewEntryInteractor = viewEntryInteractor;
    }

    public void editImages(Integer id) {
        final ViewEntryInputData viewEntryInputData = new ViewEntryInputData(id);
        viewEntryInteractor.editImages(viewEntryInputData);
    }

    public void editText(Integer id) {
        final ViewEntryInputData viewEntryInputData = new ViewEntryInputData(id);
        viewEntryInteractor.editText(viewEntryInputData);
    }

    public void updateCoords(Integer id) {
        final ViewEntryInputData viewEntryInputData = new ViewEntryInputData(id);
        viewEntryInteractor.updateCoords(viewEntryInputData);
    }

}
