package change_sort;

import data_access.EntryDataAccess;
import entity.Entry;

import org.junit.jupiter.api.Test;
import use_case.change_sort.*;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
public class ChangeSortInteractorTest {

    @Test
    public void sortAscendingTest() {
        EntryDataAccess data = new EntryDataAccess();
        Entry entry1 = data.createEntry();
        Entry entry2 = data.createEntry();

        entry1.setDate("1733014800"); // Dec 1st
        entry2.setDate("1733101200"); // Dec 2nd

    }
}
