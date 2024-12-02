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

    public String getLocation() {
        return location;
    }

    public double getTemperature() {
        return temperature;
    }

    public String getDescription() {
        return description;
    }
}
