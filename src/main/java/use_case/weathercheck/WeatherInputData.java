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

    public double getLongitude() {
        return longitude;
    }
    public double getLatitude() {
        return latitude;
    }
}
