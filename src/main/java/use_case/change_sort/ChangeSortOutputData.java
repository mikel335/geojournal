package use_case.change_sort;

import java.util.ArrayList;

/**
 * The output data of the change sort use case.
 */
public record ChangeSortOutputData(SortMethod sortMethod, ArrayList<EntryListButtonData> orderedEntries) {
}