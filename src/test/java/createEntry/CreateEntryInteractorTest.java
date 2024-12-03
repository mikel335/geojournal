package createEntry;

import data_access.EntryDataAccess;

import entity.Entry;
import org.junit.jupiter.api.Test;
import use_case.createEntry.*;


import static org.junit.jupiter.api.Assertions.*;

class CreateEntryInteractorTest {

    @Test
    void successCreateEntryTest() {
        EntryDataAccess data = new EntryDataAccess();

        Entry entry1 = data.createEntry();
        Entry entry2 = data.createEntry();
        int entry1Id = entry1.getId();
        int entry2Id = entry2.getId();

        CreateEntryOutputBoundary presenter = new CreateEntryOutputBoundary() {
            @Override
            public void prepareSuccessView(CreateEntryOutputData output) {
                assertEquals("New Entry",output.title());
                assertEquals("", entry2.getDescription());
                assertEquals(entry1Id, entry1.getId());
                assertEquals(entry2Id, entry2.getId());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case success is unexpected.");
            }
        };
        CreateEntryInputBoundary interactor = new CreateEntryInteractor(data, presenter);
        interactor.execute();
    }
}
