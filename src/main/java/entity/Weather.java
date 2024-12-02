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
     * Gets the location associated with this weather
     * @return The name of the location associated with this weather
     */
    public String getLocation() {
        return location;
    }

    /**
     * Gets the temperature
     * @return The temperature
     */
    public double getTemperature() {
        return temperature;
    }

    /**
     * Gets the description of the weather
     * @return The description of the weather
     */
    public String getDescription() {
        return description;
    }
}
