package data_access;

import entity.Entry;
import use_case.editImages.EditImagesDataAccessInterface;
import use_case.open_entry.OpenEntryDataAccessInterface;
import use_case.updateCoords.UpdateCoordsDataAccessInterface;
import use_case.updateText.UpdateTextDataAccessInterface;
import use_case.viewEntry.ViewEntryDataAccessInterface;

import java.util.Collections;
import java.util.Map;

public class EntryDataAccess implements EditImagesDataAccessInterface,
        UpdateCoordsDataAccessInterface,
        UpdateTextDataAccessInterface,
        ViewEntryDataAccessInterface,
        OpenEntryDataAccessInterface {

    private Entry currentEntry;
    private Map<Integer, Entry> allEntries;


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
