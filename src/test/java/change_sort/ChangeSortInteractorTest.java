package change_sort;

import data_access.EntryDataAccess;
import entity.Entry;
import entity.EntryList;

import org.junit.jupiter.api.Test;
import use_case.change_sort.*;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
public class ChangeSortInteractorTest {

    @Test
    void changeSortTest() {
        Entry entry1 = new Entry(
                1,
                "Test Title 1",
                "Test Description 1",
                new HashMap<>(),
                43.6532,
                -79.3832
        );
        Entry entry2 = new Entry(
                2,
                "Test Title 2",
                "Test Description 2",
                new HashMap<>(),
                43.6532,
                -79.3832
        );
        EntryList entrylist = new EntryList(1);
        ChangeSortOutputBoundary presenter = new ChangeSortOutputBoundary() {
            @Override
            public void prepareSuccessView(ChangeSortOutputData outputData) {

            }

            @Override
            public void prepareFailView(String errorMessage) {

            }
        };

    }
}
