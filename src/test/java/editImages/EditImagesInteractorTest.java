package editImages;

import data_access.EntryDataAccess;

import entity.Entry;
import org.junit.jupiter.api.Test;
import use_case.editImages.*;

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
                assertEquals("test imagepath", entry.getImagePaths().get(0));
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case failure is unexpected.");
            }

            @Override
            public void prepareDoneEditingView(EditImagesOutputData outputData) {

            }

        };

        EditImagesInputBoundary interactor = new EditImagesInteractor(data, presenter);
        interactor.addImage(addImageInputData);
    }
}

