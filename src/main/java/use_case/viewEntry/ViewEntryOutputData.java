package use_case.viewEntry;

public class ViewEntryOutputData {

    private final double latitude;
    private final double longitude;

    public ViewEntryOutputData(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}

