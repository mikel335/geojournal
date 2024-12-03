package use_case.weathercheck;

/**
 * The Input Data for the Weather Use Case.
 */
public class WeatherInputData {
    private final double longitude;
    private final double latitude;

    public WeatherInputData(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    /**
     * Gets the longitude of the location of the weather.
     * @return The longitude of the location of the weather
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Gets the latitude of the location of the weather.
     * @return The latitude of the location of the weather
     */
    public double getLatitude() {
        return latitude;
    }
}
