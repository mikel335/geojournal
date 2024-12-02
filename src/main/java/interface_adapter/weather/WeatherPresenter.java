package interface_adapter.weather;

import use_case.weathercheck.WeatherOutputBoundary;
import use_case.weathercheck.WeatherOutputData;

/**
 * The Presenter for the Weather Use Case.
 */
public class WeatherPresenter implements WeatherOutputBoundary {

    private final WeatherViewModel weatherViewModel;

    public WeatherPresenter(WeatherViewModel weatherViewModel) {
        this.weatherViewModel = weatherViewModel;
    }

        @Override
        public void prepareSuccessView(WeatherOutputData response) {
            WeatherState state = weatherViewModel.getState();
            state.setLocation(response.getLocation());
            state.setTemperature(response.getTemperature());
            state.setDescription(response.getDescription());
            state.setErrorMessage(null);
            weatherViewModel.firePropertyChanged();
        }

        @Override
        public void prepareFailView(String error) {
            WeatherState state = weatherViewModel.getState();
            state.setErrorMessage(error);
            weatherViewModel.firePropertyChanged();
        }
    }
