package use_case.weathercheck;

public class WeatherOutputData {
    private final String location;
    private final double temperature;
    private final String description;

    public WeatherOutputData(String location, double temperature, String description) {
        this.location = location;
        this.temperature = temperature;
        this.description = description;
    }

    /**
     * Gets the location of the weather
     * @return The location of the weather
     */
    public String getLocation() {
        return location;
    }

    /**
     * Gets the temperature of the weather.
     * @return The temperature of the weather
     */
    public double getTemperature() {
        return temperature;
    }

    /**
     * Gets the description of the weather.
     * @return The description of the weather
     */
    public String getDescription() {
        return description;
    }
}
