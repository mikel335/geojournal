package view;

import data_access.WeatherDataAccess;
import interface_adapter.weather.WeatherController;
import interface_adapter.weather.WeatherPresenter;
import interface_adapter.weather.WeatherState;
import interface_adapter.weather.WeatherViewModel;
import use_case.weathercheck.WeatherInputBoundary;
import use_case.weathercheck.WeatherInputData;
import use_case.weathercheck.WeatherInteractor;
import use_case.weathercheck.WeatherOutputBoundary;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WeatherView extends JFrame implements ActionListener, PropertyChangeListener {

    private final WeatherViewModel weatherViewModel;
    private WeatherController weatherController;
    private final JTextField longitudeInputField = new JTextField(10);
    private final JTextField latitudeInputField = new JTextField(10);

    private final JLabel locationLabel;
    private final JLabel temperatureLabel;
    private final JLabel descriptionLabel;
    private final JButton checkWeatherButton;

    public WeatherView(WeatherViewModel weatherViewModel, WeatherController weatherController) {
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        this.weatherViewModel = weatherViewModel;
        this.weatherController = weatherController;
        weatherViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel(WeatherViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JLabel longitudeLabel = new JLabel(WeatherViewModel.LONGITUDE_LABEL);
        final JLabel latitudeLabel = new JLabel(WeatherViewModel.LATITUDE_LABEL);

        final JPanel longitudePanel = new JPanel();
        longitudePanel.add(longitudeLabel);
        longitudePanel.add(longitudeInputField);

        final JPanel latitudePanel = new JPanel();
        latitudePanel.add(latitudeLabel);
        latitudePanel.add(latitudeInputField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        checkWeatherButton = new JButton(WeatherViewModel.CHECK_WEATHER_LABEL);
        checkWeatherButton.addActionListener(this);
        buttonPanel.add(checkWeatherButton);

        locationLabel = new JLabel(WeatherViewModel.LOCATION_LABEL);
        temperatureLabel = new JLabel(WeatherViewModel.TEMPERATURE_LABEL);
        descriptionLabel = new JLabel(WeatherViewModel.DESCRIPTION_LABEL);

        // Add components to the frame
        add(title, BorderLayout.NORTH);
        add(longitudePanel, BorderLayout.EAST);
        add(latitudePanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        JPanel infoPanel = new JPanel(new GridLayout(3, 1));
        infoPanel.add(locationLabel);
        infoPanel.add(temperatureLabel);
        infoPanel.add(descriptionLabel);
        add(infoPanel, BorderLayout.WEST);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == checkWeatherButton) {
            try {
                double longitude = Double.parseDouble(longitudeInputField.getText());
                double latitude = Double.parseDouble(latitudeInputField.getText());
                if (weatherController != null) {
                    weatherController.execute(longitude, latitude);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Invalid input. Please enter valid numerical values for longitude and latitude.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getNewValue() instanceof WeatherState) {
            final WeatherState state = (WeatherState) evt.getNewValue();
            if (state.getErrorMessage() != null) {
                JOptionPane.showMessageDialog(this, state.getErrorMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                locationLabel.setText(WeatherViewModel.LOCATION_LABEL + ": " + state.getLocation());
                temperatureLabel.setText(WeatherViewModel.TEMPERATURE_LABEL + ": " + state.getTemperature());
                descriptionLabel.setText(WeatherViewModel.DESCRIPTION_LABEL + ": " + state.getDescription());
            }
        }
    }

    public void setWeatherController(WeatherController weatherController) {
        this.weatherController = weatherController;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Instantiate WeatherView with a new WeatherViewModel and WeatherController
            final WeatherDataAccess weatherDataAccessObject = new WeatherDataAccess();
            final WeatherOutputBoundary weatherOutputBoundary = new WeatherPresenter(new WeatherViewModel());
            final WeatherInputBoundary weatherInteractor = new WeatherInteractor(weatherDataAccessObject,
                    weatherOutputBoundary);
            WeatherView demo = new WeatherView(new WeatherViewModel(), new WeatherController(weatherInteractor));
            demo.setVisible(true);
        });
    }
}

