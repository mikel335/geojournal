package entity;

public class Weather {
    private final String location;
    private final double temperature;
    private final String description;

    public Weather(String location, double temperature, String description) {
        this.location = location;
        this.temperature = temperature;
        this.description = description;
    }

    /**
     * Gets the location associated to this weather.
     * @return The location associated with this weather
     */
    public String getLocation() {
        return location;
    }

    /**
     * Gets the temperature associated with this weather.
     * @return The temperature associated with this weather
     */
    public double getTemperature() {
        return temperature;
    }

    /**
     * Gets the description of this weather.
     * @return The description of this weather
     */
    public String getDescription() {
        return description;
    }
}
