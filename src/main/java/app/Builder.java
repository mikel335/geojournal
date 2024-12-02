package app;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import data_access.DataAccessObject;
import data_access.EntryDataAccess;
import entity.EntryFactory;
import data_access.EntryDataAccess;
import entity.EntryListFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.change_sort.ChangeSortController;
import interface_adapter.change_sort.ChangeSortPresenter;
import interface_adapter.change_sort.ListViewModel;
import interface_adapter.open_entry.OpenEntryController;
import interface_adapter.open_entry.OpenEntryPresenter;
import interface_adapter.viewEntry.ViewEntryViewModel;
import interface_adapter.editImages.EditImagesController;
import interface_adapter.editImages.EditImagesPresenter;
import interface_adapter.editImages.EditImagesViewModel;
import interface_adapter.updateCoords.UpdateCoordsController;
import interface_adapter.updateCoords.UpdateCoordsPresenter;
import interface_adapter.updateCoords.UpdateCoordsViewModel;
import interface_adapter.updateText.UpdateTextController;
import interface_adapter.updateText.UpdateTextPresenter;
import interface_adapter.updateText.UpdateTextViewModel;
import interface_adapter.viewEntry.ViewEntryController;
import interface_adapter.viewEntry.ViewEntryPresenter;
import interface_adapter.viewEntry.ViewEntryViewModel;
import use_case.change_sort.ChangeSortInputBoundary;
import use_case.change_sort.ChangeSortInteractor;
import use_case.change_sort.ChangeSortOutputBoundary;
import use_case.open_entry.OpenEntryInputBoundary;
import use_case.open_entry.OpenEntryInteractor;
import use_case.open_entry.OpenEntryOutputBoundary;
import use_case.editImages.EditImagesInputBoundary;
import use_case.editImages.EditImagesInteractor;
import use_case.editImages.EditImagesOutputBoundary;
import use_case.updateCoords.UpdateCoordsInputBoundary;
import use_case.updateCoords.UpdateCoordsInteractor;
import use_case.updateCoords.UpdateCoordsOutputBoundary;
import use_case.updateText.UpdateTextInputBoundary;
import use_case.updateText.UpdateTextInteractor;
import use_case.updateText.UpdateTextOutputBoundary;
import use_case.viewEntry.ViewEntryInputBoundary;
import use_case.viewEntry.ViewEntryInteractor;
import use_case.viewEntry.ViewEntryOutputBoundary;
import view.EntryListView;
import view.MainEntryView;
import view.ViewManager;

// New View stuff
import view.EditImages.EditImagesView;
import view.UpdateCoords.UpdateCoordsView;
import view.UpdateText.UpdateTextView;
import view.ViewEntry.ViewEntryView;

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
    private final EntryDataAccess edao = new EntryDataAccess();

    // Filesystem storage access
    private final EntryDataAccess dataAccess = new EntryDataAccess();

    private EntryListView entryListView;
    private ListViewModel listViewModel;
    private ViewEntryViewModel viewEntryViewModel;
    private MainEntryView mainEntryView;

    // ViewEntry use case
    private ViewEntryView viewEntryView;
    private ViewEntryViewModel viewEntryViewModel;

    private EditImagesView editImagesView;
    private EditImagesViewModel editImagesViewModel;

    private UpdateCoordsView updateCoordsView;
    private UpdateCoordsViewModel updateCoordsViewModel;

    private UpdateTextViewModel updateTextViewModel;
    private UpdateTextView updateTextView;

    public Builder(){
        cardPanel.setLayout(cardLayout);
    }

    public Builder addEntryListView(){
        listViewModel = new ListViewModel();
        entryListView = new EntryListView(listViewModel);
        viewEntryViewModel = new ViewEntryViewModel();
        cardPanel.add(entryListView, entryListView.getViewName());
        return this;
    }

    public Builder addEntryView(){
        viewEntryViewModel = new ViewEntryViewModel();
        mainEntryView = new MainEntryView();
        cardPanel.add(mainEntryView, mainEntryView.getViewName());
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

    public Builder addOpenEntryUseCase(){
        final OpenEntryOutputBoundary openEntryOutputBoundary = new OpenEntryPresenter(viewEntryViewModel, viewManagerModel);
        final OpenEntryInputBoundary openEntryInteractor = new OpenEntryInteractor(edao, openEntryOutputBoundary, entryFactory);
        final OpenEntryController controller = new OpenEntryController(openEntryInteractor);
        entryListView.setOpenEntryController(controller);
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
        editImagesView = new EditImagesView(editImagesViewModel);
        cardPanel.add(editImagesView, editImagesViewModel.getViewName());
        return this;
    }

    public Builder addUpdateCoordsView() {
        updateCoordsViewModel = new UpdateCoordsViewModel();
        updateCoordsView = new UpdateCoordsView(updateCoordsViewModel);
        cardPanel.add(updateCoordsView, updateCoordsViewModel.getViewName());
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

    public Builder addUpdateTextUseCase() {
        final UpdateTextOutputBoundary updateTextPresenter = new UpdateTextPresenter(
                updateTextViewModel,
                viewEntryViewModel,
                viewManagerModel
        );

        final UpdateTextInputBoundary updateTextInteractor = new UpdateTextInteractor(
                updateTextPresenter,
                dataAccess
        );

        final UpdateTextController controller = new UpdateTextController(updateTextInteractor);
        updateTextView.setUpdateTextController(controller);

        return this;
    }

    public Builder addEditImagesUseCase() {
        final EditImagesOutputBoundary editImagesPresenter = new EditImagesPresenter(
                editImagesViewModel,
                viewEntryViewModel,
                viewManagerModel
        );

        final EditImagesInputBoundary editImagesInteractor = new EditImagesInteractor(
                dataAccess,
                editImagesPresenter
        );

        final EditImagesController controller = new EditImagesController(editImagesInteractor);
        editImagesView.setEditImagesController(controller);

        return this;
    }

    public Builder addUpdateCoordsUseCase() {
        final UpdateCoordsOutputBoundary updateCoordsPresenter = new UpdateCoordsPresenter(
            updateCoordsViewModel,
            viewEntryViewModel,
            viewManagerModel
        );

        final UpdateCoordsInputBoundary updateCoordsInteractor = new UpdateCoordsInteractor(
                dataAccess,
                updateCoordsPresenter
        );

        final UpdateCoordsController controller = new UpdateCoordsController(updateCoordsInteractor);
        updateCoordsView.setUpdateCoordsController(controller);

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