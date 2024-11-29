package data_access;

import entity.Weather;
import org.json.JSONObject;
import use_case.weathercheck.WeatherDataAccessInterface;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherDataAccess implements WeatherDataAccessInterface {
    private static final String API_KEY = "e018fea1997526d1a426dc116d1338c5";
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather";

    @Override
    public Weather checkWeather(double latitude, double longitude) throws Exception {
        // Build the API URL
        String urlString = BASE_URL + "?lat=" + latitude + "&lon=" + longitude + "&units=metric&appid=" + API_KEY;
        URL url = new URL(urlString);

        // Open the connection
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        // Read the response
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        // Analyze the JSON response
        JSONObject jsonResponse = new JSONObject(response.toString());
        String weatherDescription = jsonResponse.getJSONArray("weather").
                getJSONObject(0).getString("description");
        String locationName = jsonResponse.getString("name");
        double temperature = jsonResponse.getJSONObject("main").getDouble("temp");

        // Create and return the Weather
        return new Weather(locationName, temperature, weatherDescription);
    }
}