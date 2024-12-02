package data_access;

import entity.Entry;
import entity.EntryFactory;
import entity.EntryList;
import use_case.change_sort.ChangeSortDataAccessInterface;
import use_case.editImages.EditImagesDataAccessInterface;
import use_case.open_entry.OpenEntryDataAccessInterface;
import use_case.updateCoords.UpdateCoordsDataAccessInterface;
import use_case.updateText.UpdateTextDataAccessInterface;
import use_case.viewEntry.ViewEntryDataAccessInterface;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;

import org.json.JSONObject;

public class EntryDataAccess implements EditImagesDataAccessInterface,
        UpdateCoordsDataAccessInterface,
        UpdateTextDataAccessInterface,
        ViewEntryDataAccessInterface,
        OpenEntryDataAccessInterface,
        ChangeSortDataAccessInterface {

    private Entry currentEntry;
    private final Map<Integer, Entry> allEntries;
    private static final String geoJournalLocationData = System.getProperty("user.home") + File.separator + "geoJournalApplicationData";
    // dateToNameNew sorts by newest entry to oldest, the other one is the other way around
    private final TreeMap<String, Integer> nameToDate = new TreeMap<String, Integer>();
    private final TreeMap<Integer, String> dateToNameNew = new TreeMap<Integer, String>(Collections.reverseOrder());
    private final TreeMap<Integer, String> dateToNameOld = new TreeMap<Integer, String>();
    private final TreeMap<Integer, String> idToName = new TreeMap<Integer, String>();
    private final TreeMap<String, Integer> nameToID = new TreeMap<String, Integer>();


    public EntryDataAccess() {
        final EntryFactory entryFactory = new EntryFactory();
        allEntries = new HashMap<Integer, Entry>();
        HashMap<Integer, String> path = new HashMap<>();
        path.put(123, "123");
        allEntries.put(1, entryFactory.createEntry(1, "One","This is one", path, 1.1, 1.1, "Jan 1"));
        allEntries.put(2, entryFactory.createEntry(2, "Two","This is two", path, 2.2, 2.2, "Feb 2"));
        allEntries.put(3, entryFactory.createEntry(3, "three","This is three", path, 3.3, 3.3, "Mar 3"));
        nameToDate.put("one", 1);
        nameToDate.put("two", 2);
        nameToDate.put("three", 3);
        dateToNameOld.put(1, "one");
        dateToNameOld.put(2, "two");
        dateToNameOld.put(3, "three");
        dateToNameNew.put(3, "three");
        dateToNameNew.put(2, "two");
        dateToNameNew.put(1, "one");
        idToName.put(1, "one");
        idToName.put(2, "two");
        idToName.put(3, "three");
        nameToID.put("one", 1);
        nameToID.put("two", 2);
        nameToID.put("three", 3);
    }
    // TODO for testing
    public Map<Integer, Entry> getAllEntries() {
        return allEntries;
    }
    // TODO remove this. This is for testing purposes
    public EntryDataAccess(int i) {

        this.currentEntry = new Entry(
                1,
                "Test Title",
                "Test Description",
                new HashMap<>(),
                43.6532,
                -79.3832,
                "test date"
        );
        this.allEntries = new HashMap<>();
        this.allEntries.put(1, this.currentEntry);
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

    public Entry getEntry(Integer id) { return allEntries.get(id); }

    private void save() {}
        //TODO filesystem stuff for persistent storage
    public static void main(String [] args) {
        EntryDataAccess entryDataAccess = new EntryDataAccess();
        entryDataAccess.readApplicationData();
        for(Entry entry : entryDataAccess.getAllEntries().values()) {
            System.out.println("Entries Generated");
            System.out.println(entry.getId());
            System.out.println(entry.getTitle());
            System.out.println(entry.getDescription());
            System.out.println(entry.getLatitude());
            System.out.println(entry.getLongitude());
            System.out.println(entry.getImagePaths());
        }
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


    public void saveEntryData() {
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
            entries[2].add(nameToID.get(dateToName.get(i)).toString());
        }
        entryList.setEntries(entries);
    }
}
