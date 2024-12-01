package use_case.weather;

import entity.Weather;
import org.junit.jupiter.api.Test;
import use_case.weathercheck.*;

import static org.junit.jupiter.api.Assertions.*;

class WeatherInteractorTest {

    @Test
    void successTest() {
        WeatherInputData inputData = new WeatherInputData(43.659923, -79.396743); // Coordinates for Bahen Centre at UOFT.

        WeatherDataAccessInterface mockWeatherDataAccess = new WeatherDataAccessInterface() {
            @Override
            public Weather checkWeather(double latitude, double longitude) {
                return new Weather("Downtown Toronto", 0.67, "light snow");
            }
        };

        WeatherOutputBoundary successPresenter = new WeatherOutputBoundary() {
            @Override
            public void prepareSuccessView(WeatherOutputData outputData) {
                // It is important to notice that this test assumes a predetermined weather input.
                // In real time the app is sensible and depends on the latest weather station reading,
                // so the location may vary slightly between readings at different stations, as well as the weather.
                // I.e. sometimes you may get a reading from Seaton Village and other times from Downtown Toronto.
                // Weather also varies from time to time and is constantly being updated.
                assertEquals("Downtown Toronto", outputData.getLocation());
                assertEquals(0.67, outputData.getTemperature());
                assertEquals("light snow", outputData.getDescription());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case failure is unexpected.");
            }
        };

        WeatherInteractor interactor = new WeatherInteractor(mockWeatherDataAccess, successPresenter);
        interactor.checkweather(inputData);
    }

    @Test
    void failureTest() {
        WeatherInputData inputData = new WeatherInputData(43.659923, -79.396743);

        WeatherDataAccessInterface mockWeatherDataAccess = new WeatherDataAccessInterface() {
            @Override
            public Weather checkWeather(double latitude, double longitude) throws Exception {
                throw new Exception("Data access error");
            }
        };

        WeatherOutputBoundary failurePresenter = new WeatherOutputBoundary() {
            @Override
            public void prepareSuccessView(WeatherOutputData outputData) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("Error: Unable to check weather data", errorMessage);
            }
        };

        WeatherInteractor interactor = new WeatherInteractor(mockWeatherDataAccess, failurePresenter);
        interactor.checkweather(inputData);
    }
}

