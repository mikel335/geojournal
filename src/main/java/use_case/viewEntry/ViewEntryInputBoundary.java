package use_case.viewEntry;

public interface ViewEntryInputBoundary {

    /**
     * Executes the add image use case
     * @param updateCoordsInputData new coordinate data
     */
    void execute(ViewEntryInputData updateCoordsInputData);
}
