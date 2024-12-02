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
                LocalDate date1 = LocalDate.parse(o1.getDate());
                LocalDate date2 = LocalDate.parse(o2.getDate());
                return date1.compareTo(date2);
            }
            case DATE_DESCENDING -> {
                LocalDate date1 = LocalDate.parse(o1.getDate());
                LocalDate date2 = LocalDate.parse(o2.getDate());
                return date2.compareTo(date1);
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
