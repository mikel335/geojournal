package updateText;

import data_access.EntryDataAccess;

import entity.Entry;
import org.junit.jupiter.api.Test;
import use_case.updateText.*;

import static org.junit.jupiter.api.Assertions.*;

class UpdateTextInteractorTest {

    @Test
    void successTitleTest() {
        UpdateTextInputData textInputData = new UpdateTextInputData("Spain", "Beautiful country");
        EntryDataAccess data = new EntryDataAccess();
        Entry entry = data.createEntry();

        entry.setTitle("Spain");

        UpdateTextOutputBoundary presenter = new UpdateTextOutputBoundary() {
            @Override
            public void prepareSuccessView(UpdateTextOutputData outputData) {
                assertEquals("Spain", entry.getTitle());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case failure is unexpected.");
            }
        };

        UpdateTextInputBoundary interactor = new UpdateTextInteractor(presenter, data);
        interactor.execute(textInputData);
    }

    @Test
    void successDescriptionTest() {
        UpdateTextInputData textInputData = new UpdateTextInputData("Spain", "Beautiful country");
        EntryDataAccess data = new EntryDataAccess();
        Entry entry = data.createEntry();

        entry.setDescription("Beautiful country");

        UpdateTextOutputBoundary presenter = new UpdateTextOutputBoundary() {
            @Override
            public void prepareSuccessView(UpdateTextOutputData outputData) {
                assertEquals("Beautiful country", outputData.description());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case failure is unexpected.");
            }
        };

        UpdateTextInputBoundary interactor = new UpdateTextInteractor(presenter, data);
        interactor.execute(textInputData);
    }

    @Test
    void successCancelTest() {
        EntryDataAccess data = new EntryDataAccess();
        Entry entry = data.createEntry();
        entry.setTitle("Test Title 1");
        entry.setDescription("Test Description 1");

        UpdateTextOutputBoundary presenter = new UpdateTextOutputBoundary() {
            @Override
            public void prepareSuccessView(UpdateTextOutputData outputData) {
                assertEquals("Test Title 1", entry.getTitle());
                assertEquals("Test Description 1", entry.getDescription());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case failure is unexpected.");
            }
        };

        UpdateTextInputBoundary interactor = new UpdateTextInteractor(presenter, data);
        interactor.cancelUpdate();
    }
}