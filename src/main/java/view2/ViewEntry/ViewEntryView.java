package view2.ViewEntry;

import interface_adapter.viewEntry.ViewEntryController;
import interface_adapter.viewEntry.ViewEntryState;
import interface_adapter.viewEntry.ViewEntryViewModel;
import view2.ViewEntry.ImagesCard.ImagesCard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ViewEntryView extends JPanel implements ActionListener, PropertyChangeListener {

    private final ViewEntryViewModel viewModel;
    private ViewEntryController controller;
    private final JPanel contentCards;

    private final TitleDescription titleDescView;
    private final ImagesCard imagesCard;
    private final MapCard mapCard;

    public ViewEntryView(ViewEntryViewModel viewModel) {
        this.viewModel = viewModel;
        this.viewModel.addPropertyChangeListener(this);

        setSize(1200, 800);
        setLayout(new BorderLayout());

        // Set up the two content cards for the images and the map
        this.imagesCard = new ImagesCard(
                this.viewModel.getState().getImagePaths()
        );
        this.mapCard = new MapCard(
                this.viewModel.getState().getLatitude(),
                this.viewModel.getState().getLongitude()
        );
        this.contentCards = new JPanel(new CardLayout());
        contentCards.add(imagesCard, "Images");
        contentCards.add(mapCard, "Map");
        contentCards.setPreferredSize(new Dimension(900, 600));


        // Set up the display area for the title and description
        titleDescView = new TitleDescription(
                this.viewModel.getState().getTitle(),
                this.viewModel.getState().getDescription()
        );

        /*
         **** Add everything to view
         */
        add(getSwitchCardButtons(), BorderLayout.NORTH);
        add(titleDescView, BorderLayout.WEST);
        add(contentCards, BorderLayout.CENTER);
    }

    private JPanel getSwitchCardButtons() {
        JPanel switchTabsButtons = new JPanel(new BorderLayout());
        switchTabsButtons.setLayout(new BoxLayout(switchTabsButtons, BoxLayout.X_AXIS));
        JButton imagesButton = new JButton("Images");
        JButton mapButton = new JButton("Map");

        // Action listeners for card and image and text buttons
        imagesButton.addActionListener(_ -> {
            CardLayout cl = (CardLayout) contentCards.getLayout();
            cl.show(contentCards, "Images");
            imagesButton.setEnabled(false);
            mapButton.setEnabled(true);
        });

        mapButton.addActionListener(_ -> {
            CardLayout cl = (CardLayout) contentCards.getLayout();
            cl.show(contentCards, "Map");
            imagesButton.setEnabled(true);
            mapButton.setEnabled(false);
        });

        switchTabsButtons.add(imagesButton);
        switchTabsButtons.add(mapButton);

        return switchTabsButtons;
    }

    // TODO Add the ability to go to edit screens
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    // This should work
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final ViewEntryState newState = (ViewEntryState) evt.getNewValue();

        titleDescView.setTitle(newState.getTitle());
        titleDescView.setDescription(newState.getDescription());
        imagesCard.updateImagePaths(newState.getImagePaths());
        mapCard.updateCoords(newState.getLatitude(), newState.getLongitude());
    }

    public void addController(ViewEntryController controller) {
        this.controller = controller;
    }

    public String getViewName() {
        return "list";
    }
}
