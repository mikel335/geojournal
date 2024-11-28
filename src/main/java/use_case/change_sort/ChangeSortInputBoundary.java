package use_case.change_sort;

/**
 * The change sort use case.
 */
public interface ChangeSortInputBoundary {
    /**
     * Execute the change sort use case.
     * @param changeSortInputData the input data for this use case.
     */
    void execute(ChangeSortInputData changeSortInputData);
}
