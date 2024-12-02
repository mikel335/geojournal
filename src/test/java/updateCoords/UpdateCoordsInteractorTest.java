package updateCoords;

import data_access.EntryDataAccess;

import org.junit.jupiter.api.Test;
import use_case.updateCoords.*;

import static org.junit.jupiter.api.Assertions.*;

class UpdateCoordsInteractorTest {

    @Test
    void updateCoordsTest() {
        UpdateCoordsInputData coordsInputData = new UpdateCoordsInputData("100", "40");
        EntryDataAccess data = new EntryDataAccess();

        data.getCurrentEntry().setLongitude(100);
        data.getCurrentEntry().setLatitude(40);

        UpdateCoordsOutputBoundary presenter = new UpdateCoordsOutputBoundary() {
            @Override
            public void prepareSuccessView(UpdateCoordsOutputData outputData) {
                assertEquals(data.getCurrentEntry().getLongitude(), 100);
                assertEquals(data.getCurrentEntry().getLatitude(), 40);
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
    void coordinateOutOfBoundsTest() {
        UpdateCoordsInputData coordsInputData = new UpdateCoordsInputData("100", "40");
        EntryDataAccess data = new EntryDataAccess();

        data.getCurrentEntry().setLongitude(200);
        data.getCurrentEntry().setLatitude(100);

        UpdateCoordsOutputBoundary presenter = new UpdateCoordsOutputBoundary() {
            @Override
            public void prepareSuccessView(UpdateCoordsOutputData outputData) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("(Latitude: " + 100 + ", Longitude:" + 200 + ") is not a valid set of coordinates.", errorMessage);
            }

        };

        UpdateCoordsInputBoundary interactor = new UpdateCoordsInteractor(data, presenter);
        interactor.execute(coordsInputData);
    }
}
