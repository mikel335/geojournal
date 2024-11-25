package data_access;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;

import entity.Entry;
import entity.EntryFactory;
import entity.EntryList;
import use_case.change_sort.ChangeSortDataAccessInterface;


public class DataAccessObject implements ChangeSortDataAccessInterface{

    // dateToNameNew sorts by newest entry to oldest, the other one is the other way around
    private final TreeMap<String, Integer> nameToDate = new TreeMap<String, Integer>();
    private final TreeMap<Integer, String> dateToNameNew = new TreeMap<Integer, String>(Collections.reverseOrder());
    private final TreeMap<Integer, String> dateToNameOld = new TreeMap<Integer, String>();

    public DataAccessObject(EntryList entryList) {
        // TODO: populate the treemaps, need to implement file system
        // this constructor is for sorting purposes (to pass in an entrylist)
    }

    public DataAccessObject() {
        // TODO: populate the treemaps, need to implement file system
        // this version is for init purposes
    }

    @Override
    public void changeSortAndUpdate(EntryList entryList) {
        final int sort = entryList.getSortMethod();
        ArrayList<String>[] entries = new ArrayList[2];
        TreeMap<Integer, String> dateToName;
        if (sort == 0) {
            dateToName = dateToNameOld;
        } else {
            dateToName = dateToNameNew;
        }
        for (Integer i : dateToName.descendingKeySet()) {
            entries[0].add(dateToName.get(i));
            entries[1].add(i.toString());
        }
        entryList.setEntries(entries);
    }
}