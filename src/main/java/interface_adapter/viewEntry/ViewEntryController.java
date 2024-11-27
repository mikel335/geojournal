package interface_adapter.viewEntry;

import use_case.viewEntry.ViewEntryInputData;
import use_case.viewEntry.ViewEntryInputBoundary;


public class ViewEntryController {
    private final ViewEntryInputBoundary viewEntryInteractor;

    public ViewEntryController(ViewEntryInputBoundary viewEntryInteractor) {
        this.viewEntryInteractor = viewEntryInteractor;
    }

    public void execute(double latitude, double longitude) {
        final ViewEntryInputData viewEntryInputData = new ViewEntryInputData(latitude, longitude);
        viewEntryInteractor.execute(viewEntryInputData);
    }
}
