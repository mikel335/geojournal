package interface_adapter.change_sort;

import use_case.change_sort.ChangeSortInputBoundary;
import use_case.change_sort.ChangeSortInputData;

/**
 * Controller for the change sort use case.
 */
public class ChangeSortController {
    private final ChangeSortInputBoundary entryListChangeSortUseCaseBoundary;

    public ChangeSortController(ChangeSortInputBoundary changeSortUseCaseBoundary) {
        this.entryListChangeSortUseCaseBoundary = changeSortUseCaseBoundary;
    }

    /**
     * Executes the change sort use case.
     * @param sortMethod the new sort method
     */
    public void execute(int sortMethod) {
        final ChangeSortInputData changeSortInputData = new ChangeSortInputData(sortMethod);

        entryListChangeSortUseCaseBoundary.execute(changeSortInputData);
    }
}