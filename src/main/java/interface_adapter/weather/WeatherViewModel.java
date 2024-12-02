package interface_adapter.weather;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the Weather View.
 */
public class WeatherViewModel extends ViewModel<WeatherState> {

    public static final String TITLE_LABEL = "Weather";
    public static final String LONGITUDE_LABEL = "Please enter a longitude:";
    public static final String LATITUDE_LABEL = "Please enter a latitude:";
    public static final String TEMPERATURE_LABEL = "Temperature:";
    public static final String DESCRIPTION_LABEL = "Description:";
    public static final String LOCATION_LABEL = "Location:";
    public static final String CHECK_WEATHER_LABEL = "Check Weather";

    public WeatherViewModel() {
        super("weather");
        setState(new WeatherState());
    }
}

