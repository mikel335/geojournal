package view.ViewEntry;

import interface_adapter.weather.WeatherController;
import interface_adapter.weather.WeatherState;
import interface_adapter.weather.WeatherViewModel;
import view.Components.Colors;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class WeatherData extends JPanel implements PropertyChangeListener {
    private WeatherController weatherController;

    private final JLabel temperature;
    private final JLabel location;
    private final JLabel description;

    public WeatherData(WeatherViewModel weatherViewModel) {
        weatherViewModel.addPropertyChangeListener(this);
        setBorder(BorderFactory.createEmptyBorder(20,0,20,0));

        this.temperature = new JLabel(Double.toString(weatherViewModel.getState().getTemperature()));
        this.location = new JLabel(weatherViewModel.getState().getLocation());
        this.description = new JLabel(weatherViewModel.getState().getDescription());

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Colors.lightBlue);
        setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel temperaturePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        temperaturePanel.setBackground(Colors.lightBlue);
        JLabel temperatureLabel = new JLabel("Temperature: ");
        temperatureLabel.setFont(new Font("sans serif", Font.BOLD, 20));
        temperature.setFont(new Font("sans serif", Font.BOLD, 20));
        temperaturePanel.add(temperatureLabel);
        temperaturePanel.add(this.temperature);

        JPanel locationPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        locationPanel.setBackground(Colors.lightBlue);
        JLabel locationLabel = new JLabel("Location: ");
        locationLabel.setFont(new Font("sans serif", Font.BOLD, 20));
        location.setFont(new Font("sans serif", Font.BOLD, 20));
        locationPanel.add(locationLabel);
        locationPanel.add(this.location);

        JPanel descriptionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        descriptionPanel.setBackground(Colors.lightBlue);
        JLabel descriptionLabel = new JLabel("Description: ");
        descriptionLabel.setFont(new Font("sans serif", Font.BOLD, 20));
        description.setFont(new Font("sans serif", Font.BOLD, 20));

        descriptionPanel.add(descriptionLabel);
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
