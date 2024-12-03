package use_case.change_sort;


/**
 * The input data for the change sort use case.
 */
public class ChangeSortInputData {
    private final SortMethod sort_method;

    public ChangeSortInputData(SortMethod sort_method) {
        this.sort_method = sort_method;
    }

    /**
     * Gets the new sort method.
     * @return The new sort method
     */
    SortMethod getSortMethod() {
        return sort_method;
    }
}