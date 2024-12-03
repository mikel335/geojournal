package open_entry;

import data_access.EntryDataAccess;

import entity.Entry;
import org.junit.jupiter.api.Test;
import use_case.open_entry.*;

import static org.junit.jupiter.api.Assertions.*;

class OpenEntryInteractorTest {

    @Test
    void SucessOpenEntryTest(){
        EntryDataAccess data = new EntryDataAccess();
        OpenEntryInputData openEntryInputData = new OpenEntryInputData(2);

        Entry entry1 = data.createEntry();
        data.createEntry();
        Entry entry3 = data.createEntry();

        OpenEntryOutputBoundary presenter = new OpenEntryOutputBoundary() {
            @Override
            public void prepareSuccessView(OpenEntryOutputData outputData) {
                assertEquals(3, entry3.getId());
                assertEquals(1, entry1.getId());
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