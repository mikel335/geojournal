package use_case.weathercheck;

import entity.Weather;

public class WeatherInteractor implements WeatherInputBoundary {
    private final WeatherDataAccessInterface weatherDataAccessObject;
    private final WeatherOutputBoundary weatherPresenter;

    public WeatherInteractor(WeatherDataAccessInterface weatherDataAccessInterface,
                             WeatherOutputBoundary weatherOutputBoundary) {
        this.weatherDataAccessObject = weatherDataAccessInterface;
        this.weatherPresenter = weatherOutputBoundary;
    }

    @Override
    public void checkWeather(WeatherInputData inputData) {
        try {
            Weather weather = weatherDataAccessObject.checkWeather(inputData.getLatitude(), inputData.getLongitude());
            WeatherOutputData outputData = new WeatherOutputData(
                    weather.getLocation(),
                    weather.getTemperature(),
                    weather.getDescription()
            );
            weatherPresenter.prepareSuccessView(outputData);
        } catch (Exception unableCheckWeather) {
            String errorOutput = new String("Error:" + " Unable to check weather data");
            weatherPresenter.prepareFailView(errorOutput);
        }
    }
}
