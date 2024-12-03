package open_entry;

import data_access.EntryDataAccess;

import org.junit.jupiter.api.Test;
import use_case.open_entry.*;

import static org.junit.jupiter.api.Assertions.*;

class OpenEntryInteractorTest {

    @Test
    void SucessOpenEntryTest(){
        EntryDataAccess data = new EntryDataAccess();
        OpenEntryInputData openEntryInputData = new OpenEntryInputData(2);

        data.createEntry();
        data.createEntry();
        data.createEntry();
        data.createEntry();

        OpenEntryOutputBoundary presenter = new OpenEntryOutputBoundary() {
            @Override
            public void prepareSuccessView(OpenEntryOutputData outputData) {
                assertEquals(2, data.getAllEntries().get(2).getId());
                assertEquals(0, data.getAllEntries().get(0).getId());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case success is unexpected.");
            }
        };
        OpenEntryInputBoundary interactor = new OpenEntryInteractor(data, presenter);
        interactor.execute(openEntryInputData);

    }
}
