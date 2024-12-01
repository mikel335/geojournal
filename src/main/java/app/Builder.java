package app;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import data_access.DataAccessObject;
import data_access.EntryDataAccess;
import entity.EntryListFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.change_sort.ChangeSortController;
import interface_adapter.change_sort.ChangeSortPresenter;
import interface_adapter.change_sort.ListViewModel;
import interface_adapter.editImages.EditImagesViewModel;
import interface_adapter.updateCoords.UpdateCoordsViewModel;
import interface_adapter.updateText.UpdateTextViewModel;
import interface_adapter.viewEntry.ViewEntryController;
import interface_adapter.viewEntry.ViewEntryPresenter;
import interface_adapter.viewEntry.ViewEntryViewModel;
import use_case.change_sort.ChangeSortInputBoundary;
import use_case.change_sort.ChangeSortInteractor;
import use_case.change_sort.ChangeSortOutputBoundary;
import use_case.viewEntry.ViewEntryInputBoundary;
import use_case.viewEntry.ViewEntryInteractor;
import use_case.viewEntry.ViewEntryOutputBoundary;
import view.EntryListView;
import view.ViewManager;

// New View stuff
import viewWithCA.UpdateText.UpdateTextView;
import viewWithCA.ViewEntry.ViewEntryView;

/**
 * The Builder puts the CA architecture together.
 */
public class Builder{
    // Set up layout to display / hide diff views
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();

    // TODO figure out if we need this EntryFactory
    // private final EntryFactory entryFactory = new EntryFactory();
    private final EntryListFactory entryListFactory = new EntryListFactory();

    // View Manager to manage which view to display
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    private final DataAccessObject dao = new DataAccessObject();

    // Filesystem storage access
    private final EntryDataAccess dataAccess = new EntryDataAccess();

    private EntryListView entryListView;
    private ListViewModel listViewModel;

    // ViewEntry use case
    private ViewEntryView viewEntryView;
    private ViewEntryViewModel viewEntryViewModel;

    // TODO EditImages use case
    private EditImagesViewModel editImagesViewModel;

    // TODO updateCoords use case
    private UpdateCoordsViewModel updateCoordsViewModel;

    private UpdateTextViewModel updateTextViewModel;
    private UpdateTextView updateTextView;

    public Builder(){
        cardPanel.setLayout(cardLayout);
    }

    public Builder addEntryListView(){
        listViewModel = new ListViewModel();
        entryListView = new EntryListView(listViewModel);
        cardPanel.add(entryListView, entryListView.getViewName());
        return this;
    }

    public Builder addChangeSortUseCase(){
        final ChangeSortOutputBoundary changeSortOutputBoundary = new ChangeSortPresenter(viewManagerModel, listViewModel);
        final ChangeSortInputBoundary changeSortInteractor = new ChangeSortInteractor(
                dao,changeSortOutputBoundary, entryListFactory);
        final ChangeSortController controller = new ChangeSortController(changeSortInteractor);
        entryListView.setChangeSortController(controller);
        return this;
    }

    public Builder addViewEntryView() {
        viewEntryViewModel = new ViewEntryViewModel();
        viewEntryView = new ViewEntryView(viewEntryViewModel);
        cardPanel.add(viewEntryView, viewEntryViewModel.getViewName());
        return this;
    };

    public Builder addEditImagesView() {
        editImagesViewModel = new EditImagesViewModel();
        // TODO build EditImagesView
        return this;
    }

    public Builder addUpdateCoordsView() {
        updateCoordsViewModel = new UpdateCoordsViewModel();
        // TODO build UpdateCoordsView
        return this;
    }

    public Builder addUpdateTextView() {
        updateTextViewModel = new UpdateTextViewModel();
        updateTextView = new UpdateTextView(updateTextViewModel);
        cardPanel.add(updateTextView, updateTextViewModel.getViewName());
        return this;
    }

    public Builder addViewEntryUseCase() {
        final ViewEntryOutputBoundary viewEntryPresenter = new ViewEntryPresenter(
                viewEntryViewModel,
                editImagesViewModel,
                updateCoordsViewModel,
                updateTextViewModel,
                viewManagerModel);

        final ViewEntryInputBoundary viewEntryInteractor = new ViewEntryInteractor(
                viewEntryPresenter,
                dataAccess
        );

        final ViewEntryController controller = new ViewEntryController(viewEntryInteractor);
        viewEntryView.addController(controller);
        return this;
    }

    public JFrame build(){
        final JFrame application = new JFrame("Entries");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.add(cardPanel);

        // TODO change this back to the entry list
        viewManagerModel.setState(viewEntryViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

        return application;
    }
}