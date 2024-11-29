package data_access;

import entity.Entry;
import entity.EntryFactory;
import use_case.editImages.EditImagesDataAccessInterface;
import use_case.open_entry.OpenEntryDataAccessInterface;
import use_case.updateCoords.UpdateCoordsDataAccessInterface;
import use_case.updateText.UpdateTextDataAccessInterface;
import use_case.viewEntry.ViewEntryDataAccessInterface;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class EntryDataAccess implements EditImagesDataAccessInterface,
        UpdateCoordsDataAccessInterface,
        UpdateTextDataAccessInterface,
        ViewEntryDataAccessInterface,
        OpenEntryDataAccessInterface {

    private Entry currentEntry;
    private Map<Integer, Entry> allEntries;

    public EntryDataAccess() {
        final EntryFactory entryFactory = new EntryFactory();
        allEntries = new HashMap<Integer, Entry>();
        HashMap<Integer, String> path = new HashMap<>();
        path.put(123, "123");
        allEntries.put(1, entryFactory.createEntry(1, "One","This is one", path, 1.1, 1.1, "Jan 1"));
        allEntries.put(2, entryFactory.createEntry(2, "Two","This is two", path, 2.2, 2.2, "Feb 2"));
        allEntries.put(3, entryFactory.createEntry(3, "three","This is three", path, 3.3, 3.3, "Mar 3"));
    }

    @Override
    public void addImageToCurrentEntry(String imagePath) {
        Integer newId = Collections.max(currentEntry.getImagePaths().keySet()) + 1;
        currentEntry.addImagePath(newId, imagePath);
        save();
    }

    @Override
    public void deleteImageFromCurrentEntry(Integer id) {
        currentEntry.removeImagePath(id);
        save();
    }

    @Override
    public void setCoordinates(double latitude, double longitude) {
        currentEntry.setLatitude(latitude);
        currentEntry.setLongitude(longitude);
        save();
    }

    @Override
    public void setTitle(String title) {
        currentEntry.setTitle(title);
        save();
    }

    @Override
    public void setDescription(String description) {
        currentEntry.setDescription(description);
        save();
    }

    @Override
    public Entry getCurrentEntry() {
        return currentEntry;
    }

    public Entry getEntry(Integer id) { return allEntries.get(id); }

    private void save() {
        //TODO filesystem stuff for persistent storage
    }

    @Override
    public Entry getEntry(int id) {
        return allEntries.get(id);
    }
}
