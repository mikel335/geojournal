package data_access;

import entity.Entry;
import use_case.editImages.EditImagesDataAccessInterface;
import use_case.updateCoords.UpdateCoordsDataAccessInterface;
import use_case.updateText.UpdateTextDataAccessInterface;
import use_case.viewEntry.ViewEntryDataAccessInterface;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

public class EntryDataAccess implements EditImagesDataAccessInterface,
        UpdateCoordsDataAccessInterface,
        UpdateTextDataAccessInterface,
        ViewEntryDataAccessInterface {

    private Entry currentEntry;
    private final Map<Integer, Entry> allEntries;
    private static final String geoJournalLocationData = System.getProperty("user.home") + File.separator + "geoJournalApplicationData";


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

    public static void main(String [] args) {
        EntryDataAccess test = new EntryDataAccess();
        test.saveEntryData();
    }

    private void readFile() {

    }

    public void saveEntryData() {
        File dataFolder = new File(geoJournalLocationData);
        File dataFile = new File(geoJournalLocationData + File.separator + "data.json");
        try {
            if (dataFolder.mkdir()) {
                System.out.println("Created a new folder at" + geoJournalLocationData);
            } else {
                System.out.println("Folder already exists");
            }
            if (dataFile.createNewFile()) {
                System.out.println("Created new data file at " + dataFile.getAbsolutePath());
            }
            else {
                System.out.println("File already exists");
            }
            if (serializeApplicationDataToFile(dataFile)) {
                System.out.println("Saved application data successfully");
            }

        } catch(IOException e) {
            System.out.println("There was an issue saving the data");
            e.printStackTrace();
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
            e.printStackTrace();
        }



    }

    private boolean serializeApplicationDataToFile(File file) {
        Map<String, Map<String, String>> applicationData = new HashMap<>();
        for (Integer entryId : allEntries.keySet()) {
            applicationData.put(Integer.toString(entryId), getEntryKeyValues(allEntries.get(entryId)));
        }

        try {
            FileWriter dataWriter = new FileWriter(file, false);
            dataWriter.write(new JSONObject(applicationData).toString());
            dataWriter.close();
            return true;
        } catch (IOException _) {
            return false;
        }
    }

    private static Map<String, String> getEntryKeyValues(Entry entry) {
        Map<String, String> entryValues = new HashMap<>();
        entryValues.put("title", entry.getTitle());
        entryValues.put("description", entry.getDescription());
        entryValues.put("latitude", String.valueOf(entry.getLatitude()));
        entryValues.put("longitude", String.valueOf(entry.getLongitude()));
        entryValues.put("id", String.valueOf(entry.getId()));
        entryValues.put("imageIds", entry.getImagePaths().toString());
        return entryValues;
    }
}
