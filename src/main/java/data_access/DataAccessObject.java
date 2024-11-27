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
    private final TreeMap<Integer, String> idToName = new TreeMap<Integer, String>();

    public DataAccessObject() {
        // TODO: populate the treemaps, need to implement file system
        // this version is for init purposes
        nameToDate.put("one", 1);
        nameToDate.put("two", 2);
        nameToDate.put("three", 3);
        dateToNameOld.put(1, "one");
        dateToNameOld.put(2, "two");
        dateToNameOld.put(3, "three");
        dateToNameNew.put(3, "three");
        dateToNameNew.put(2, "two");
        dateToNameNew.put(1, "one");
    }

    @Override
    public void changeSortAndUpdate(EntryList entryList) {
        final int sort = entryList.getSortMethod();
        ArrayList<String>[] entries = new ArrayList[3];
        entries[0] = new ArrayList<String>();
        entries[1] = new ArrayList<String>();
        entries[2] = new ArrayList<String>();
        TreeMap<Integer, String> dateToName;
        if (sort == 1) {
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