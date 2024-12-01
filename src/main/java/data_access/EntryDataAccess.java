package data_access;

import entity.Entry;
import use_case.editImages.EditImagesDataAccessInterface;
import use_case.updateCoords.UpdateCoordsDataAccessInterface;
import use_case.updateText.UpdateTextDataAccessInterface;
import use_case.viewEntry.ViewEntryDataAccessInterface;

import java.util.Collections;
import java.util.Map;

public class EntryDataAccess implements EditImagesDataAccessInterface,
        UpdateCoordsDataAccessInterface,
        UpdateTextDataAccessInterface,
        ViewEntryDataAccessInterface {

    private Entry currentEntry;
    private Map<Integer, Entry> allEntries;


    @Override
    public void addImageToCurrentEntry(String imagePath) {
        // TODO figure out if I need this
        int id = currentEntry.addImagePath(imagePath);
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

    private void save() {
        //TODO filesystem stuff for persistent storage
    }
}
