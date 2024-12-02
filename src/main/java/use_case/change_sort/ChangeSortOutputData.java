package use_case.change_sort;

import java.util.ArrayList;

/**
 * The output data of the change sort use case.
 */
public class ChangeSortOutputData {
    private final int sortMethod;
    private final boolean useCaseFailed;
    private final ArrayList<String>[] entries;

    public ChangeSortOutputData(int sortMethod, boolean useCaseFailed, ArrayList<String>[] entries) {
        this.sortMethod = sortMethod;
        this.useCaseFailed = useCaseFailed;
        this.entries = entries;
    }

    public int getSortMethod() {
        return sortMethod;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }

    public ArrayList<String>[] getEntries() {
        return entries;
    }

}