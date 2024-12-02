package use_case.weathercheck;

/**
 * Input Boundary for actions which are related to weather.
 */
public interface WeatherInputBoundary {
    /**
     * Executes the weather use case.
     * @param inputData the input data
     */
    void checkweather (WeatherInputData inputData);
}

