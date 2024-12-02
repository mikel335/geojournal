package use_case.viewEntry;

import entity.Entry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ViewEntryInteractorTest {

    private TestViewEntryPresenter testPresenter;
    private TestViewEntryDataAccess testDataAccess;
    private ViewEntryInteractor interactor;

    @BeforeEach
    void setUp() {
        testPresenter = new TestViewEntryPresenter();
        testDataAccess = new TestViewEntryDataAccess();
        interactor = new ViewEntryInteractor(testPresenter, testDataAccess);
    }

    @Test
    void testEditImagesSuccess() {
        // Arrange
        Map<Integer, String> images = new HashMap<>();
        images.put(0, "image1.jpg");
        images.put(1, "image2.jpg");

        Entry entry = new Entry(1, "Title", "Description", images, 10.0, 20.0);
        testDataAccess.setMockEntry(entry);

        // Act
        interactor.editImages();

        // Assert
        assertTrue(testPresenter.successCalled);
        assertEquals("Title", testPresenter.outputData.getTitle());
        assertEquals("Description", testPresenter.outputData.getDescription());
        assertEquals(images, testPresenter.outputData.getImagePaths());
        assertEquals(10.0, testPresenter.outputData.getLatitude());
        assertEquals(20.0, testPresenter.outputData.getLongitude());
    }

    @Test
    void testEditImagesFailure() {
        // Arrange
        testDataAccess.throwException = true;

        // Act
        interactor.editImages();

        // Assert
        assertTrue(testPresenter.failCalled);
        assertEquals("There was an issue retrieving data for the current entry", testPresenter.errorMessage);
    }

    @Test
    void testViewEntrySuccess() {
        // Arrange
        Map<Integer, String> images = new HashMap<>();
        images.put(0, "image1.jpg");

        Entry entry = new Entry(1, "Title", "Description", images, 15.0, 25.0);
        testDataAccess.setMockEntry(entry);

        // Act
        interactor.viewEntry();

        // Assert
        assertTrue(testPresenter.successCalled);
        assertEquals("Title", testPresenter.outputData.getTitle());
        assertEquals("Description", testPresenter.outputData.getDescription());
        assertEquals(images, testPresenter.outputData.getImagePaths());
        assertEquals(15.0, testPresenter.outputData.getLatitude());
        assertEquals(25.0, testPresenter.outputData.getLongitude());
    }

    @Test
    void testViewEntryFailure() {
        // Arrange
        testDataAccess.throwException = true;

        // Act
        interactor.viewEntry();

        // Assert
        assertTrue(testPresenter.failCalled);
        assertEquals("There was an issue retrieving data for the current entry", testPresenter.errorMessage);
    }

    // Additional test cases for updateCoords and editText can follow the same pattern.

    // Test stub for ViewEntryOutputBoundary
    static class TestViewEntryPresenter implements ViewEntryOutputBoundary {
        boolean successCalled = false;
        boolean failCalled = false;
        String errorMessage;
        ViewEntryOutputData outputData;

        @Override
        public void prepareEditImagesView(ViewEntryOutputData outputData) {
            this.successCalled = true;
            this.outputData = outputData;
        }

        @Override
        public void prepareUpdateCoordsView(ViewEntryOutputData outputData) {
            this.successCalled = true;
            this.outputData = outputData;
        }

        @Override
        public void prepareEditTextView(ViewEntryOutputData outputData) {
            this.successCalled = true;
            this.outputData = outputData;
        }

        @Override
        public void prepareViewEntryView(ViewEntryOutputData outputData) {
            this.successCalled = true;
            this.outputData = outputData;
        }

        @Override
        public void prepareFailView(String errorMessage) {
            this.failCalled = true;
            this.errorMessage = errorMessage;
        }
    }

    // Test stub for ViewEntryDataAccessInterface
    static class TestViewEntryDataAccess implements ViewEntryDataAccessInterface {
        private Entry mockEntry;
        boolean throwException = false;

        void setMockEntry(Entry mockEntry) {
            this.mockEntry = mockEntry;
        }

        @Override
        public Entry getCurrentEntry() {
            if (throwException) {
                throw new RuntimeException("Data access error");
            }
            return mockEntry;
        }
    }
}