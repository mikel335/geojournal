package updateText;

import data_access.EntryDataAccess;

import org.junit.jupiter.api.Test;
import use_case.updateText.*;

import static org.junit.jupiter.api.Assertions.*;

class UpdateTextInteractorTest {

    @Test
    void updateTitleTest() {
        UpdateTextInputData textInputData = new UpdateTextInputData("Spain", "Beautiful country");
        EntryDataAccess data = new EntryDataAccess();

        data.getCurrentEntry().setTitle("Spain");

        UpdateTextOutputBoundary presenter = new UpdateTextOutputBoundary() {
            @Override
            public void prepareSuccessView(UpdateTextOutputData outputData) {
                assertEquals("Spain", data.getCurrentEntry().getTitle());
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
    void updateDescriptionTest() {
        UpdateTextInputData textInputData = new UpdateTextInputData("Spain", "Beautiful country");
        EntryDataAccess data = new EntryDataAccess();

        data.getCurrentEntry().setDescription("Beautiful country");

        UpdateTextOutputBoundary presenter = new UpdateTextOutputBoundary() {
            @Override
            public void prepareSuccessView(UpdateTextOutputData outputData) {
                assertEquals("Beautiful country", data.getCurrentEntry().getDescription());
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
    void cancelTest() {
        EntryDataAccess data = new EntryDataAccess();

        UpdateTextOutputBoundary presenter = new UpdateTextOutputBoundary() {
            @Override
            public void prepareSuccessView(UpdateTextOutputData outputData) {
                assertEquals("Test Title", data.getCurrentEntry().getTitle());
                assertEquals("Test Description", data.getCurrentEntry().getDescription());
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
