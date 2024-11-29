package interface_adapter.weather;

import use_case.weathercheck.WeatherInputBoundary;
import use_case.weathercheck.WeatherInputData;

/**
 * Controller for the Weather Use Case.
 */
public class WeatherController {

    private final WeatherInputBoundary weatherUseCaseInteractor;

    public WeatherController(WeatherInputBoundary weatherUseCaseInteractor) {
        this.weatherUseCaseInteractor = weatherUseCaseInteractor;
    }

    /**
     * Executes the Weather Use Case.
     * @param longitude the longitude of the location
     * @param latitude the latitude of the location
     */
    public void execute(double longitude, double latitude) {
        final WeatherInputData signupInputData = new WeatherInputData(longitude, latitude);

        weatherUseCaseInteractor.checkweather(signupInputData);
    }

//    /**
//     * Refreshes weather data using default location or previously stored coordinates.
//     * This is useful for a "Refresh" button in the View.
//     */
//    public void refreshWeather() {
//        // Provide default coordinates or retrieve them from a stored state.
//        double defaultLongitude = 0.0;
//        double defaultLatitude = 0.0;
//        execute(defaultLongitude, defaultLatitude);
//    }
}
