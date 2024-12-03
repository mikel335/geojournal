package interface_adapter.weather;

/**
 * The state of the Weather view model.
 */
public class WeatherState {
    private double temperature;
    private String description;
    private String location;
    private String errorMessage;

    /**
     * Gets the temperature associated with this weather.
     * @return The temperature associated with this weather
     */
    public double getTemperature() {
        return temperature;
    }

    /**
     * Sets the temperature associated with this weather.
     * @param temperature The new temperature associated with this weather
     */
    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    /**
     * Gets the description of this weather.
     * @return The description of this weather
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description associated with this weather.
     * @param description The new description associated with this weather
     */
    public void setDescription(String description) {
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
     * Sets the location associated with this weather.
     * @param location The new location associated with this weather
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Gets the error message associated with the weather fetching use case
     * @return The error message associated with the weather fetching
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Sets the error message associated with the weather fetching use case
     * @param errorMessage The new error message to be associated with the weather fetching
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}

