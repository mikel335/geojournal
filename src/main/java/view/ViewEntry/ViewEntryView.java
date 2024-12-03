package view.ViewEntry;

import interface_adapter.viewEntry.ViewEntryController;
import interface_adapter.viewEntry.ViewEntryState;
import interface_adapter.viewEntry.ViewEntryViewModel;
import interface_adapter.weather.WeatherController;
import interface_adapter.weather.WeatherViewModel;
import view.Components.Colors;
import view.Components.ImageDisplayPanel;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ViewEntryView extends JPanel implements PropertyChangeListener {
    private ViewEntryController controller;

    private final TitleDescription titleDescArea;
    private final ImageDisplayPanel imagesCard;
    private final MapCard mapCard;

    private final WeatherData weatherData;

    private final EditOptions editOptions;

    public ViewEntryView(ViewEntryViewModel viewModel, WeatherViewModel weatherViewModel) {
        viewModel.addPropertyChangeListener(this);
        setBackground(Colors.lightBlue);

        setSize(1200, 800);
        setLayout(new BorderLayout(30, 30));
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        // Prefill everything with the state data
        this.imagesCard = new ImageDisplayPanel(viewModel.getState().getImagePaths(), false);
        this.mapCard = new MapCard(
                viewModel.getState().getLatitude(),
                viewModel.getState().getLongitude()
        );

        titleDescArea = new TitleDescription(
                viewModel.getState().getTitle(),
                viewModel.getState().getDescription()
        );

        weatherData = new WeatherData(
                weatherViewModel
        );

        // Set up the edit options. Controller will be updated with this class
        editOptions = new EditOptions(controller);

        // Content cards contain the images and map view
        JPanel contentCards = new JPanel(new CardLayout());
        contentCards.setBackground(Colors.lightBlue);
        contentCards.add(imagesCard, "Images");
        contentCards.add(mapCard, "Map");
        contentCards.setPreferredSize(new Dimension(900, 600));

        // Header containing tab selection buttons
        JPanel selectHeader = new SelectCardHeader(contentCards);

        // Panel containing title, description and edit buttons
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBackground(Colors.lightBlue);

        JPanel lowerLeftPanel = new JPanel(new BorderLayout());
        lowerLeftPanel.setBackground(Colors.lightBlue);

        lowerLeftPanel.add(weatherData, BorderLayout.NORTH);
        lowerLeftPanel.add(editOptions, BorderLayout.SOUTH);

        leftPanel.add(titleDescArea, BorderLayout.CENTER);
        leftPanel.add(lowerLeftPanel, BorderLayout.SOUTH);

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBackground(Colors.lightBlue);
        rightPanel.add(selectHeader, BorderLayout.NORTH);
        rightPanel.add(contentCards, BorderLayout.CENTER);

        /*
         **** Add everything to view
         */
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);
    }

    // Get new state data when firePropertyChanged is called on ViewEntryState
    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        if (evt.getNewValue() instanceof ViewEntryState newState) {
            if (newState.getViewEntryError() != null) {
                JOptionPane.showMessageDialog(
                        this,
                        newState.getViewEntryError(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            } else {
                titleDescArea.setTitle(newState.getTitle());
                titleDescArea.setDescription(newState.getDescription());
                imagesCard.updateImagePaths(newState.getImagePaths());
                mapCard.updateCoords(newState.getLatitude(), newState.getLongitude());
                weatherData.refreshWeatherData(newState.getLongitude(), newState.getLatitude());
            }
        }
    }

    /**
     * Adds the view entry controller.
     * @param controller The controller to use for the view entry use case
     */
    public void addViewEntryController(ViewEntryController controller) {
        this.controller = controller;
        this.editOptions.setViewEntryController(controller);
    }

    /**
     * Adds the weather controller.
     * @param weatherController The controller for the weather fetching use case
     */
    public void addWeatherController(WeatherController weatherController) {
        weatherData.setWeatherController(weatherController);
    }
}
