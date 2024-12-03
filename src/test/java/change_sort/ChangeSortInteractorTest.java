package change_sort;

import data_access.EntryDataAccess;
import entity.Entry;

import org.junit.jupiter.api.Test;
import use_case.change_sort.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ChangeSortInteractorTest {

    @Test
    void successSortDateAscendingTest() {
        EntryDataAccess data = new EntryDataAccess();
        ChangeSortInputData changeSortInputData = new ChangeSortInputData(SortMethod.DATE_ASCENDING);
        Entry entry1 = data.createEntry();
        Entry entry2 = data.createEntry();
        Entry entry3 = data.createEntry();

        entry1.setDate("1733187600"); // Dec 3rd
        entry2.setDate("0"); // Epoch start
        entry3.setDate("1733101200"); // Dec 2nd

        EntryListButtonDataComparitor comparator = new EntryListButtonDataComparitor(SortMethod.DATE_ASCENDING);
        ArrayList<EntryListButtonData> list = new ArrayList<>();
        list.add(new EntryListButtonData(entry1));
        list.add(new EntryListButtonData(entry2));
        list.add(new EntryListButtonData(entry3));
        list.sort(comparator);

        ChangeSortOutputBoundary presenter = new ChangeSortOutputBoundary() {
            @Override
            public void prepareSuccessView(ChangeSortOutputData outputData) {
                assertEquals(list.getFirst().getDate(), "0");
                assertEquals("1733187600", list.getLast().getDate());
                assertEquals(entry2.getId(), list.getFirst().getId()); // Entry 3 has ID 3
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case success is unexpected.");
            }
        };
        ChangeSortInputBoundary interactor = new ChangeSortInteractor(data, presenter);
        interactor.execute(changeSortInputData);
    }

    @Test
    void successSortTitleAscendingTest() {
        EntryDataAccess data = new EntryDataAccess();
        ChangeSortInputData changeSortInputData = new ChangeSortInputData(SortMethod.TITLE_ASCENDING);
        Entry entry1 = data.createEntry();
        Entry entry2 = data.createEntry();

        entry2.setTitle("a");
        entry1.setTitle("Z");
        EntryListButtonDataComparitor comparitor = new EntryListButtonDataComparitor(SortMethod.TITLE_ASCENDING);
        ArrayList<EntryListButtonData> list = new ArrayList<>();
        list.add(new EntryListButtonData(entry1));
        list.add(new EntryListButtonData(entry2));
        list.sort(comparitor);

        ChangeSortOutputBoundary presenter = new ChangeSortOutputBoundary() {
            @Override
            public void prepareSuccessView(ChangeSortOutputData outputData) {
                assertEquals("Z", list.getFirst().getTitle());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case success is unexpected.");
            }
        };
        ChangeSortInputBoundary interactor = new ChangeSortInteractor(data, presenter);
        interactor.execute(changeSortInputData);
    }

    @Test
    void successSortDateDescendingTest() {
        EntryDataAccess data = new EntryDataAccess();
        ChangeSortInputData changeSortInputData = new ChangeSortInputData(SortMethod.DATE_DESCENDING);
        Entry entry1 = data.createEntry();
        Entry entry2 = data.createEntry();

        entry2.setTitle("a");
        entry1.setTitle("Z");
        EntryListButtonDataComparitor comparator = new EntryListButtonDataComparitor(SortMethod.DATE_DESCENDING);
        ArrayList<EntryListButtonData> list = new ArrayList<>();
        list.add(new EntryListButtonData(entry1));
        list.add(new EntryListButtonData(entry2));
        list.sort(comparator);

        ChangeSortOutputBoundary presenter = new ChangeSortOutputBoundary() {
            @Override
            public void prepareSuccessView(ChangeSortOutputData outputData) {
                assertEquals("Z", list.getFirst().getTitle());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case success is unexpected.");
            }
        };
        ChangeSortInputBoundary interactor = new ChangeSortInteractor(data, presenter);
        interactor.execute(changeSortInputData);
    }

    @Test
    public void successSameTitleTest() {
        EntryDataAccess data = new EntryDataAccess();
        ChangeSortInputData changeSortInputData = new ChangeSortInputData(SortMethod.TITLE_DESCENDING);
        Entry entry1 = data.createEntry();
        Entry entry2 = data.createEntry();
        Entry entry3 = data.createEntry();

        entry3.setTitle("a");
        entry2.setTitle("a");
        entry1.setTitle("a");
        entry2.setDate("1733014800"); // Dec 1st
        entry1.setDate("1733101200"); // Dec 2nd
        entry3.setDate("1733187600"); // Dec 3rd

        EntryListButtonDataComparitor comparator = new EntryListButtonDataComparitor(SortMethod.TITLE_DESCENDING);
        ArrayList<EntryListButtonData> list = new ArrayList<>();
        list.add(new EntryListButtonData(entry1));
        list.add(new EntryListButtonData(entry2));
        list.add(new EntryListButtonData(entry3));
        list.sort(comparator);

        ChangeSortOutputBoundary presenter = new ChangeSortOutputBoundary() {
            @Override
            public void prepareSuccessView(ChangeSortOutputData outputData) {
                assertEquals("1733101200", list.getFirst().getDate());
                assertEquals("1733187600", list.getLast().getDate());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case success is unexpected.");
            }
        };
        ChangeSortInputBoundary interactor = new ChangeSortInteractor(data, presenter);
        interactor.execute(changeSortInputData);
    }
}
