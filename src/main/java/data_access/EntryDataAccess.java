package data_access;

import entity.Entry;
import use_case.change_sort.ChangeSortDataAccessInterface;
import use_case.createEntry.CreateEntryDataAccessInterface;
import use_case.editImages.EditImagesDataAccessInterface;
import use_case.open_entry.OpenEntryDataAccessInterface;
import use_case.updateCoords.UpdateCoordsDataAccessInterface;
import use_case.updateText.UpdateTextDataAccessInterface;
import use_case.viewEntry.ViewEntryDataAccessInterface;

import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.InputStream;

import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


import org.json.JSONObject;

public class EntryDataAccess implements EditImagesDataAccessInterface,
        UpdateCoordsDataAccessInterface,
        UpdateTextDataAccessInterface,
        ViewEntryDataAccessInterface,
        OpenEntryDataAccessInterface,
        ChangeSortDataAccessInterface,
        CreateEntryDataAccessInterface {

    private Entry currentEntry;
    private final Map<Integer, Entry> allEntries;
    private static final String geoJournalLocationData = System.getProperty("user.home") + File.separator + "geoJournalApplicationData";

    public EntryDataAccess() {
        this.allEntries = new HashMap<>();
        readApplicationData();
    }

    /**
     * Sets the current entry to the one with the given ID
     * @param id The id of the entry to set as the current entry
     */
    public void setCurrentEntry(int id) {
        this.currentEntry = allEntries.get(id);
    }

    @Override
    public void addImageToCurrentEntry(String imagePath) {
        // Make sure all the folders exist
        createImageFolders();

        // Copy the image to the images folder
        InputStream is;

        try {
            File imgDestination = getImgDestination(imagePath);

            is = new FileInputStream(imagePath);
            Files.copy(is, imgDestination.toPath(), StandardCopyOption.REPLACE_EXISTING);

            // Update in memory
            this.currentEntry.addImagePath(imgDestination.getAbsolutePath());

            // Save entry data to make sure that img path is saved
            saveEntryData();

        } catch (IOException e) {
            System.out.println("There was a problem copying the image");
            System.out.println(e.getMessage());
        }
    }

    private File getImgDestination(String imagePath) {

        File imageFile = new File(imagePath);
        String[] splitUpFileName = imageFile.getName().split("\\.");

        return new File(
                geoJournalLocationData
                        + File.separator
                        + "images"
                        + File.separator
                        + currentEntry.getId() +
                        File.separator
                        + currentEntry.getNewImgId()
                        + "."
                        + splitUpFileName[splitUpFileName.length - 1]
        );
    }

    @Override
    public void deleteImageFromCurrentEntry(Integer id) {
        currentEntry.removeImagePath(id);
        saveEntryData();
    }

    @Override
    public void setCoordinates(double latitude, double longitude) {
        currentEntry.setLatitude(latitude);
        currentEntry.setLongitude(longitude);
        saveEntryData();

    }

    @Override
    public void setTitle(String title) {
        currentEntry.setTitle(title);
        saveEntryData();
    }

    @Override
    public void setDescription(String description) {
        currentEntry.setDescription(description);
        saveEntryData();
    }

    @Override
    public Entry getCurrentEntry() {
        return currentEntry;
    }

    private void readApplicationData() {
        File dataFolder = new File(geoJournalLocationData);
        File dataFile = new File(geoJournalLocationData + File.separator + "data.json");
        try {
            if (dataFolder.mkdir()) {
                System.out.println("Created a new folder at" + geoJournalLocationData);
            }
            if (dataFile.createNewFile()) {
                System.out.println("Created new data file at " + dataFile.getAbsolutePath());
            }

            String jsonData = Files.readString(dataFile.toPath());

            if (!jsonData.isEmpty()) {

                JSONObject fileData = new JSONObject(jsonData);

                for (String data : fileData.keySet()) {
                    JSONObject entryData = (JSONObject) fileData.get(data);
                    this.allEntries.put(
                            Integer.parseInt(entryData.get("id").toString()),
                            createEntryFromJSON(entryData)
                    );
                }
            }

        } catch(IOException e) {
            System.out.println("There was a problem reading the data file");
            System.out.println(e.getMessage());
        }
    }

    private Entry createEntryFromJSON(JSONObject jsonObject) {
        Map<Integer, String> imgData = new HashMap<>();

        JSONObject imgIds = new JSONObject(jsonObject.getString("imageIds"));

        for (String imgId : imgIds.keySet()) {
            imgData.put(Integer.parseInt(imgId), imgIds.getString(imgId));
        }

        return new Entry(
                jsonObject.getInt("id"),
                jsonObject.getString("title"),
                jsonObject.getString("description"),
                imgData,
                jsonObject.getDouble("longitude"),
                jsonObject.getDouble("latitude"),
                jsonObject.getString("date")
            );
    }

    private void saveEntryData() {
        File dataFolder = new File(geoJournalLocationData);
        File dataFile = new File(geoJournalLocationData + File.separator + "data.json");
        try {
            if (dataFolder.mkdir()) {
                System.out.println("Created a new folder at" + geoJournalLocationData);
            }
            if (dataFile.createNewFile()) {
                System.out.println("Created new data file at " + dataFile.getAbsolutePath());
            }
            serializeApplicationDataToFile(dataFile);

        } catch(IOException e) {
            System.out.println("There was an issue saving the data");
            System.out.println(e.getMessage());
        }
    }

    private void createImageFolders() {
        File dataFolder = new File(geoJournalLocationData);
        File imageFolder = new File(geoJournalLocationData + File.separator + "images");

        try {
            if (dataFolder.mkdir()) {
                System.out.println("Created a new folder at" + geoJournalLocationData);
            }
            if (imageFolder.mkdir()) {
                System.out.println("Created Image directory at" + geoJournalLocationData + File.separator + "images");
            }

            for (Entry entry : allEntries.values()) {
                File entryImages = new File(geoJournalLocationData
                                                + File.separator
                                                + "images"
                                                + File.separator
                                                + entry.getId());
                if (entryImages.mkdir()) {
                    System.out.println("Created Image directory at"
                            + geoJournalLocationData
                            + File.separator
                            + "images"
                            + File.separator
                            + entry.getId()
                    );
                }
            }
        } catch(Exception e) {
            System.out.println("There was an issue saving the images");
            System.out.println(e.getMessage());
        }
    }

    private void serializeApplicationDataToFile(File file) {
        JSONObject appData = new JSONObject();

        for (Integer entryId : allEntries.keySet()) {
            appData.put(Integer.toString(entryId), serializeEntry(allEntries.get(entryId)));
        }

        try {
            Files.writeString(file.toPath(), appData.toString());
        } catch (IOException _) {
            System.out.println("Failed to serialize application data");
        }
    }

    private static JSONObject serializeEntry(Entry entry) {
        JSONObject entryValues = new JSONObject();

        entryValues.put("title", entry.getTitle());
        entryValues.put("description", entry.getDescription());
        entryValues.put("latitude", String.valueOf(entry.getLatitude()));
        entryValues.put("longitude", String.valueOf(entry.getLongitude()));
        entryValues.put("id", String.valueOf(entry.getId()));
        entryValues.put("date", String.valueOf(entry.getDate()));

        JSONObject imgIds = new JSONObject();

        for (Integer id : entry.getImagePaths().keySet()) {
            imgIds.put(Integer.toString(id), String.valueOf(entry.getImagePaths().get(id)));
        }

        entryValues.put("imageIds", imgIds.toString());

        return entryValues;
    }

    @Override
    public Entry getEntry(int id) {
        return allEntries.get(id);
    }

    @Override
    public Map<Integer, Entry> getEntryList() {
        return this.allEntries;
    }

    @Override
    public Entry createEntry() {
        int newId = 0;

        if (!this.allEntries.isEmpty()) {
            newId = Collections.max(this.allEntries.keySet()) + 1;
        }

        Entry newEntry = new Entry(
                newId,
                "New Entry",
                "",
                new HashMap<>(),
                0,
                0,
                String.valueOf(new Date().getTime())
        );

        this.currentEntry = newEntry;
        this.allEntries.put(newId, newEntry);
        return newEntry;
    }
}
