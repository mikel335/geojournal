package updateCoords;

import data_access.EntryDataAccess;

import entity.Entry;
import org.junit.jupiter.api.Test;
import use_case.updateCoords.*;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class UpdateCoordsInteractorTest {

    @Test
    void successUpdateCoordsTest() {
        UpdateCoordsInputData coordsInputData = new UpdateCoordsInputData("100", "40");
        EntryDataAccess data = new EntryDataAccess();
        Entry entry = data.createEntry();
        entry.setLongitude(100);
        entry.setLatitude(40);

        UpdateCoordsOutputBoundary presenter = new UpdateCoordsOutputBoundary() {
            @Override
            public void prepareSuccessView(UpdateCoordsOutputData outputData) {
                assertEquals(outputData.longitude(), 100);
                assertEquals(outputData.latitude(), 40);
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case success is unexpected.");
            }
        };

        UpdateCoordsInputBoundary interactor = new UpdateCoordsInteractor(data, presenter);
        interactor.execute(coordsInputData);
    }

    @Test
    void failureCoordinateOutOfBoundsTest() {
        UpdateCoordsInputData coordsInputData = new UpdateCoordsInputData("200", "100");
        EntryDataAccess data = new EntryDataAccess();

        Entry entry = data.createEntry();
        entry.setLongitude(200);
        entry.setLatitude(100);

        UpdateCoordsOutputBoundary presenter = new UpdateCoordsOutputBoundary() {
            @Override
            public void prepareSuccessView(UpdateCoordsOutputData outputData) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("(Latitude: " + 100.0 + ", Longitude:" + 200.0 + ") is not a valid set of coordinates.", errorMessage);
            }

        };

        UpdateCoordsInputBoundary interactor = new UpdateCoordsInteractor(data, presenter);
        interactor.execute(coordsInputData);
    }
}
