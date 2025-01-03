package interface_adapter.createEntry;

import use_case.createEntry.CreateEntryInputBoundary;

public class CreateEntryController {
    private CreateEntryInputBoundary interactor;

    public CreateEntryController(CreateEntryInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Executes the creation of an entry based on the given interactor.
     */
    public void createEntry(){ this.interactor.execute(); }
}
