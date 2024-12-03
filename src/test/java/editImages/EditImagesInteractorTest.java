package editImages;

import data_access.EntryDataAccess;

import entity.Entry;
import org.junit.jupiter.api.Test;
import use_case.editImages.*;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class EditImagesInteractorTest {

    @Test
    void successTestAddImage() {
        AddImageInputData addImageInputData = new AddImageInputData("test imagepath");

        EntryDataAccess data = new EntryDataAccess();

        Entry entry = data.createEntry();

        entry.addImagePath("test imagepath");

        EditImagesOutputBoundary presenter = new EditImagesOutputBoundary() {
            @Override
            public void prepareSuccessView(EditImagesOutputData outputData) {
                assertEquals("test imagepath", outputData.imagePaths().get(0));
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case failure is unexpected.");
            }

            @Override
            public void prepareDoneEditingView(EditImagesOutputData outputData) {
                fail("Shouldn't run this.");
            }

        };

        EditImagesInputBoundary interactor = new EditImagesInteractor(data, presenter);
        interactor.addImage(addImageInputData);
    }

    @Test
    void failureTestAddImage() {
        AddImageInputData addImageInputData = new AddImageInputData("");

        EntryDataAccess data = new EntryDataAccess();

        EditImagesOutputBoundary presenter = new EditImagesOutputBoundary() {
            @Override
            public void prepareSuccessView(EditImagesOutputData outputData) {
                fail("Shouldn't run this.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertNotNull(errorMessage);
            }

            @Override
            public void prepareDoneEditingView(EditImagesOutputData outputData) {
                fail("Shouldn't run this.");
            }
        };

        EditImagesInputBoundary interactor = new EditImagesInteractor(data, presenter);
        interactor.addImage(addImageInputData);
    }

    @Test
    void successTestDeleteImage() {
        DeleteImageInputData deleteImageInputData = new DeleteImageInputData(0);

        EntryDataAccess data = new EntryDataAccess();

        Entry entry = data.createEntry();
        entry.addImagePath("test imagepath");

        EditImagesOutputBoundary presenter = new EditImagesOutputBoundary() {
            @Override
            public void prepareSuccessView(EditImagesOutputData outputData) {
                assertTrue(outputData.imagePaths().isEmpty());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case failure is unexpected.");
            }

            @Override
            public void prepareDoneEditingView(EditImagesOutputData outputData) {
                fail("Shouldn't run this.");
            }
        };

        EditImagesInputBoundary interactor = new EditImagesInteractor(data, presenter);
        interactor.deleteImage(deleteImageInputData);
    }

    @Test
    void failureTestDeleteImage() {
        DeleteImageInputData deleteImageInputData = new DeleteImageInputData(999);

        EntryDataAccess data = new EntryDataAccess();

        EditImagesOutputBoundary presenter = new EditImagesOutputBoundary() {
            @Override
            public void prepareSuccessView(EditImagesOutputData outputData) {
                fail("Shouldn't run this.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertNotNull(errorMessage);
            }

            @Override
            public void prepareDoneEditingView(EditImagesOutputData outputData) {
                fail("Shouldn't run this.");
            }
        };

        EditImagesInputBoundary interactor = new EditImagesInteractor(data, presenter);
        interactor.deleteImage(deleteImageInputData);
    }

    @Test
    void testReturnToEntryView() {
        EntryDataAccess data = new EntryDataAccess();
        Entry entry = data.createEntry();
        entry.addImagePath("test imagepath");

        EditImagesOutputBoundary presenter = new EditImagesOutputBoundary() {
            @Override
            public void prepareSuccessView(EditImagesOutputData outputData) {
                fail("Shouldn't run this.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case failure is unexpected.");
            }

            @Override
            public void prepareDoneEditingView(EditImagesOutputData outputData) {
                assertEquals(1, outputData.imagePaths().size());
                assertEquals("test imagepath", outputData.imagePaths().get(0));
            }
        };

        EditImagesInputBoundary interactor = new EditImagesInteractor(data, presenter);
        interactor.returnToEntryView();
    }

    @Test
    void failureTestReturnToEntryView() {
        EditImagesDataAccessInterface mockDataAccess = new EditImagesDataAccessInterface() {
            @Override
            public void addImageToCurrentEntry(String imagePath) {
                // Not needed for this test
            }

            @Override
            public void deleteImageFromCurrentEntry(Integer id) {
                // Not needed for this test
            }

            @Override
            public Entry getCurrentEntry() {
                throw new RuntimeException("Simulated data access failure.");
            }
        };

        EditImagesOutputBoundary presenter = new EditImagesOutputBoundary() {
            @Override
            public void prepareSuccessView(EditImagesOutputData outputData) {
                fail("Shouldn't run this.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("Simulated data access failure.", errorMessage);
            }

            @Override
            public void prepareDoneEditingView(EditImagesOutputData outputData) {
                fail("Shouldn't run this.");
            }
        };

        EditImagesInputBoundary interactor = new EditImagesInteractor(mockDataAccess, presenter);
        interactor.returnToEntryView();
    }

    @Test
    void successTestReturnToEntryView() {
        EditImagesDataAccessInterface mockDataAccess = new EditImagesDataAccessInterface() {
            @Override
            public void addImageToCurrentEntry(String imagePath) {
                // Not needed for this test
            }

            @Override
            public void deleteImageFromCurrentEntry(Integer id) {
                // Not needed for this test
            }

            @Override
            public Entry getCurrentEntry() {
                HashMap<Integer, String> imagePaths = new HashMap<>();
                Entry mockEntry = new Entry(0,"test entry", "test desc", imagePaths, 0.0,0.0, "test date");
                mockEntry.addImagePath("test imagepath");
                return mockEntry;
            }
        };

        EditImagesOutputBoundary presenter = new EditImagesOutputBoundary() {
            @Override
            public void prepareSuccessView(EditImagesOutputData outputData) {
                fail("Shouldn't run this.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Shouldn't run this.");
            }

            @Override
            public void prepareDoneEditingView(EditImagesOutputData outputData) {
                assertEquals(1, outputData.imagePaths().size());
                assertEquals("test imagepath", outputData.imagePaths().get(0));
            }
        };

        EditImagesInputBoundary interactor = new EditImagesInteractor(mockDataAccess, presenter);
        interactor.returnToEntryView();
    }
}
