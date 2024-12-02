package use_case.change_sort;

import java.time.LocalDate;
import java.util.Comparator;

public class EntryListButtonDataComparitor implements Comparator<EntryListButtonData> {

    SortMethod sortMethod;
    public EntryListButtonDataComparitor(SortMethod sortMethod) {
        this.sortMethod = sortMethod;
    }

    @Override
    public int compare(EntryListButtonData o1, EntryListButtonData o2) {
        switch (sortMethod) {
            case DATE_ASCENDING -> {
                return o1.getDate().compareTo(o2.getDate());
            }
            case DATE_DESCENDING -> {
                return o2.getDate().compareTo(o1.getDate());
            }
            case TITLE_ASCENDING -> {
                return o1.getTitle().compareTo(o2.getTitle());
            }
            case TITLE_DESCENDING -> {
                return o2.getTitle().compareTo(o1.getTitle());
            }
            default -> {
                return 0;
            }
        }
    }
}
