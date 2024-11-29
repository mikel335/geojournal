package use_case.weathercheck;

import entity.Weather;

/**
 * DAO for the Weather Use Case.
 */
public interface WeatherDataAccessInterface {
    Weather checkWeather (double latitude, double longitude) throws Exception;
}
