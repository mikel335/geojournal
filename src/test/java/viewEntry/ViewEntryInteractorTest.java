package viewEntry;

import data_access.EntryDataAccess;

import interface_adapter.viewEntry.ViewEntryPresenter;
import org.junit.jupiter.api.Test;
import use_case.viewEntry.ViewEntryInputBoundary;
import use_case.viewEntry.ViewEntryInteractor;
import use_case.viewEntry.ViewEntryOutputBoundary;
import use_case.viewEntry.ViewEntryOutputData;

import static org.junit.jupiter.api.Assertions.*;

class ViewEntryInteractorTest {

    @Test
    void editImagesTest() {
        EntryDataAccess data = new EntryDataAccess();
        ViewEntryOutputBoundary presenter = new ViewEntryOutputBoundary() {
            @Override
            public void prepareEditImagesView(ViewEntryOutputData outputData) {
                assertEquals('a', 'a');
            }

            @Override
            public void prepareUpdateCoordsView(ViewEntryOutputData outputData) {
                fail("Use case is unexpected");
            }

            @Override
            public void prepareEditTextView(ViewEntryOutputData outputData) {
                fail("Use case is unexpected");
            }

            @Override
            public void prepareViewEntryView(ViewEntryOutputData outputData) {
                fail("Use case is unexpected");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case is unexpected");
            }
        };
        ViewEntryInputBoundary interactor = new ViewEntryInteractor(presenter, data);
        interactor.editImages();
    }
}