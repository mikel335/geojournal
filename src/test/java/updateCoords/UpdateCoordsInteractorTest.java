package updateCoords;

import data_access.EntryDataAccess;

import entity.Entry;
import org.junit.jupiter.api.Test;
import use_case.updateCoords.*;

import static org.junit.jupiter.api.Assertions.*;

class UpdateCoordsInteractorTest {
    @Test
    void successUpdateCoordsTest() {
        UpdateCoordsInputData coordsInputData = new UpdateCoordsInputData("100", "40");
        EntryDataAccess data = new EntryDataAccess();
        Entry entry = data.createEntry();
        entry.setLongitude(80);
        entry.setLatitude(20);

        UpdateCoordsOutputBoundary presenter = new UpdateCoordsOutputBoundary() {
            @Override
            public void prepareSuccessView(UpdateCoordsOutputData outputData) {
                assertEquals(100, entry.getLongitude());
                assertEquals(40, entry.getLatitude());
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
    void failureLatOutOfBoundsTest() {
        UpdateCoordsInputData coordsInputData = new UpdateCoordsInputData("0", "100");
        EntryDataAccess data = new EntryDataAccess();

        Entry entry = data.createEntry();
        entry.setLongitude(80);
        entry.setLatitude(40);

        UpdateCoordsOutputBoundary presenter = new UpdateCoordsOutputBoundary() {
            @Override
            public void prepareSuccessView(UpdateCoordsOutputData outputData) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("(Latitude: " +  Double.parseDouble(coordsInputData.latitude()) + ", Longitude: " +  Double.parseDouble(coordsInputData.longitude() )+ ") is not a valid set of coordinates.", errorMessage);
                assertEquals(80, entry.getLongitude());
                assertEquals(40, entry.getLatitude());
            }
        };

        UpdateCoordsInputBoundary interactor = new UpdateCoordsInteractor(data, presenter);
        interactor.execute(coordsInputData);
    }


    @Test
    void failureLongOutOfBoundsTest() {
        UpdateCoordsInputData coordsInputData = new UpdateCoordsInputData("500.0", "0.0");
        EntryDataAccess data = new EntryDataAccess();

        Entry entry = data.createEntry();
        entry.setLongitude(80);
        entry.setLatitude(40);

        UpdateCoordsOutputBoundary presenter = new UpdateCoordsOutputBoundary() {
            @Override
            public void prepareSuccessView(UpdateCoordsOutputData outputData) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("(Latitude: " + Double.parseDouble(coordsInputData.latitude()) + ", Longitude: " + Double.parseDouble(coordsInputData.longitude()) + ") is not a valid set of coordinates.", errorMessage);
                assertEquals(80, entry.getLongitude());
                assertEquals(40, entry.getLatitude());
            }
        };

        UpdateCoordsInputBoundary interactor = new UpdateCoordsInteractor(data, presenter);
        interactor.execute(coordsInputData);
    }

    @Test
    void failureLatNotConvertableTest() {
        UpdateCoordsInputData coordsInputData = new UpdateCoordsInputData("40", "lat");
        EntryDataAccess data = new EntryDataAccess();

        Entry entry = data.createEntry();
        entry.setLongitude(100);
        entry.setLatitude(40);

        UpdateCoordsOutputBoundary presenter = new UpdateCoordsOutputBoundary() {
            @Override
            public void prepareSuccessView(UpdateCoordsOutputData outputData) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("This is not a valid latitude value: " + coordsInputData.latitude(), errorMessage);
                assertEquals(entry.getLatitude(), 40);
                assertEquals(entry.getLongitude(), 100);
            }
        };

        UpdateCoordsInputBoundary interactor = new UpdateCoordsInteractor(data, presenter);
        interactor.execute(coordsInputData);
    }

    @Test
    void failureLongNotConvertableTest() {
        UpdateCoordsInputData coordsInputData = new UpdateCoordsInputData("long", "40");
        EntryDataAccess data = new EntryDataAccess();
        Entry entry = data.createEntry();
        entry.setLongitude(100);
        entry.setLatitude(40);

        UpdateCoordsOutputBoundary presenter = new UpdateCoordsOutputBoundary() {
            @Override
            public void prepareSuccessView(UpdateCoordsOutputData outputData) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("This is not a valid longitude value: " + coordsInputData.longitude(), errorMessage);
                assertEquals(entry.getLatitude(), 40);
                assertEquals(entry.getLongitude(), 100);
            }
        };

        UpdateCoordsInputBoundary interactor = new UpdateCoordsInteractor(data, presenter);
        interactor.execute(coordsInputData);
    }
}
