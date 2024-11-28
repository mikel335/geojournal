package use_case.change_sort;

/**
 * The input data for the change sort use case.
 */
public class ChangeSortInputData {
    private final int sort_method;

    public ChangeSortInputData(int sort_method) {
        this.sort_method = sort_method;
    }

    int getSortMethod() {
        return sort_method;
    }
}