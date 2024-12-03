package view.ViewEntry;

import interface_adapter.weather.WeatherController;
import interface_adapter.weather.WeatherState;
import interface_adapter.weather.WeatherViewModel;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.FlowLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class WeatherData extends JPanel implements PropertyChangeListener {
    private WeatherController weatherController;

    private final JLabel temperature;
    private final JLabel location;
    private final JLabel description;

    public WeatherData(WeatherViewModel weatherViewModel) {
        weatherViewModel.addPropertyChangeListener(this);

        this.temperature = new JLabel(Double.toString(weatherViewModel.getState().getTemperature()));
        this.location = new JLabel(weatherViewModel.getState().getLocation());
        this.description = new JLabel(weatherViewModel.getState().getDescription());

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel temperaturePanel = new JPanel(new FlowLayout());
        temperaturePanel.add(new JLabel("Temperature: "));
        temperaturePanel.add(this.temperature);

        JPanel locationPanel = new JPanel(new FlowLayout());
        locationPanel.add(new JLabel("Location: "));
        locationPanel.add(this.location);

        JPanel descriptionPanel = new JPanel(new FlowLayout());
        descriptionPanel.add(new JLabel("Description: "));
        descriptionPanel.add(this.description);

        add(temperaturePanel);
        add(locationPanel);
        add(descriptionPanel);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getNewValue() instanceof WeatherState weatherState) {
            if (weatherState.getErrorMessage() != null) {
                JOptionPane.showMessageDialog(this, weatherState.getErrorMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                temperature.setText(Double.toString(weatherState.getTemperature()));
                location.setText(weatherState.getLocation());
                description.setText(weatherState.getDescription());
            }
        }
    }

    /**
     * Sets the controller to use for the weather fetching use case.
     * @param weatherController The controller to use for the weather fetching use case
     */
    public void setWeatherController(WeatherController weatherController) {
        this.weatherController = weatherController;
    }

    /**
     * Refreshes the weather data
     * @param latitude The latitude of the location at which to fetch the weather data of
     * @param longitude The longitude of the location at which to fetch the weather data of
     */
    public void refreshWeatherData(double latitude, double longitude) {
        if(weatherController != null)  {
            this.weatherController.execute(latitude, longitude);
        }
    }
}
