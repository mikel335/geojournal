package viewWithCA.ViewEntry;

import interface_adapter.viewEntry.ViewEntryController;
import interface_adapter.viewEntry.ViewEntryState;
import interface_adapter.viewEntry.ViewEntryViewModel;
import viewWithCA.Components.ImageDisplayPanel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ViewEntryView extends JPanel implements PropertyChangeListener {
    private ViewEntryController controller;

    private final TitleDescription titleDescArea;
    private final ImageDisplayPanel imagesCard;
    private final MapCard mapCard;

    private final EditOptions editOptions;

    public ViewEntryView(ViewEntryViewModel viewModel) {
        viewModel.addPropertyChangeListener(this);

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

        // Set up the edit options. Controller will be updated with this class
        editOptions = new EditOptions(controller);

        // Content cards contain the images and map view
        JPanel contentCards = new JPanel(new CardLayout());
        contentCards.add(imagesCard, "Images");
        contentCards.add(mapCard, "Map");
        contentCards.setPreferredSize(new Dimension(900, 600));

        // Header containing tab selection buttons
        JPanel selectHeader = new SelectCardHeader(contentCards);

        // Panel containing title, description and edit buttons
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(titleDescArea, BorderLayout.CENTER);
        leftPanel.add(editOptions, BorderLayout.SOUTH);

        JPanel rightPanel = new JPanel(new BorderLayout());
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
        final ViewEntryState newState = (ViewEntryState) evt.getNewValue();

        titleDescArea.setTitle(newState.getTitle());
        titleDescArea.setDescription(newState.getDescription());
        imagesCard.updateImagePaths(newState.getImagePaths());
        mapCard.updateCoords(newState.getLatitude(), newState.getLongitude());
    }

    public void addController(ViewEntryController controller) {
        this.controller = controller;
        this.editOptions.setViewEntryController(controller);
        controller.viewEntry();
    }
}
