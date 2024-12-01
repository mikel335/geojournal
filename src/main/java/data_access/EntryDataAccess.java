package data_access;

import entity.Entry;
import use_case.editImages.EditImagesDataAccessInterface;
import use_case.updateCoords.UpdateCoordsDataAccessInterface;
import use_case.updateText.UpdateTextDataAccessInterface;
import use_case.viewEntry.ViewEntryDataAccessInterface;

import java.util.HashMap;
import java.util.Map;

public class EntryDataAccess implements EditImagesDataAccessInterface,
        UpdateCoordsDataAccessInterface,
        UpdateTextDataAccessInterface,
        ViewEntryDataAccessInterface {

    private Entry currentEntry;
    private Map<Integer, Entry> allEntries;


    // TODO remove this. This is for testing purposes
    public EntryDataAccess() {

        this.currentEntry = new Entry(
                1,
                "Test Title",
                "Test Description",
                new HashMap<>(),
                43.6532,
                -79.3832
        );
        this.allEntries = new HashMap<>();

        this.addImageToCurrentEntry("/Users/sebastienpsarianos/Desktop/Screenshot 2024-10-07 at 12.34.17 PM.png");
        this.addImageToCurrentEntry("/Users/sebastienpsarianos/Desktop/Screenshot 2024-10-07 at 12.34.17 PM.png");
        this.addImageToCurrentEntry("/Users/sebastienpsarianos/Desktop/Screenshot 2024-10-07 at 12.34.17 PM.png");
        this.addImageToCurrentEntry("/Users/sebastienpsarianos/Desktop/Screenshot 2024-10-07 at 12.34.17 PM.png");
        this.addImageToCurrentEntry("/Users/sebastienpsarianos/Desktop/Screenshot 2024-10-07 at 12.34.17 PM.png");
        this.addImageToCurrentEntry("/Users/sebastienpsarianos/Desktop/Screenshot 2024-10-07 at 12.34.17 PM.png");
        this.addImageToCurrentEntry("/Users/sebastienpsarianos/Desktop/Screenshot 2024-10-07 at 12.34.17 PM.png");
        this.addImageToCurrentEntry("/Users/sebastienpsarianos/Desktop/Screenshot 2024-10-07 at 12.34.17 PM.png");
        this.addImageToCurrentEntry("/Users/sebastienpsarianos/Desktop/Screenshot 2024-10-07 at 12.34.17 PM.png");
        this.addImageToCurrentEntry("/Users/sebastienpsarianos/Desktop/Screenshot 2024-10-07 at 12.34.17 PM.png");

        this.allEntries.put(1, this.currentEntry);
    }

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
