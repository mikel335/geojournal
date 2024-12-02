package app;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import data_access.EntryDataAccess;
import entity.EntryFactory;
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

// New View stuff
import view.EditImages.EditImagesView;
import view.EntryListView;
import view.UpdateCoords.UpdateCoordsView;
import view.UpdateText.UpdateTextView;
import view.ViewEntry.ViewEntryView;
import view.ViewManager;

/**
 * The Builder puts the CA architecture together.
 */
public class Builder{
    // Set up layout to display / hide diff views
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();

    private final EntryFactory entryFactory = new EntryFactory();
    private final EntryListFactory entryListFactory = new EntryListFactory();

    // View Manager to manage which view to display
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    // Filesystem storage access
    private final EntryDataAccess dataAccess = new EntryDataAccess();

    private EntryListView entryListView;
    private ListViewModel listViewModel;

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

    /**
     * Creates a builder that has the entry list view and associated model
     * @return Builder that has an entry list view and its model
     */
    public Builder addEntryListView() {
        // TODO: Get the appropriate arrays out of dataAccess and input them here
        listViewModel = new ListViewModel(dataAccess);
        entryListView = new EntryListView(listViewModel);
        viewEntryViewModel = new ViewEntryViewModel();
        cardPanel.add(entryListView, entryListView.getViewName());
        return this;
    }

    /**
     * Creates a builder with all the objects needed for change sort to function
     * @return A Builder with all the objects needed for the change sort use case
     */
    public Builder addChangeSortUseCase(){
        final ChangeSortOutputBoundary changeSortOutputBoundary = new ChangeSortPresenter(viewManagerModel, listViewModel);
        final ChangeSortInputBoundary changeSortInteractor = new ChangeSortInteractor(
                dataAccess,changeSortOutputBoundary, entryListFactory);
        final ChangeSortController controller = new ChangeSortController(changeSortInteractor);
        entryListView.setChangeSortController(controller);
        return this;
    }

    /**
     * Creates a builder with all the objects needed for opening entries to function
     * @return A Builder with all the objects needed for the open entry use case
     */
    public Builder addOpenEntryUseCase(){
        final OpenEntryOutputBoundary openEntryOutputBoundary = new OpenEntryPresenter(viewEntryViewModel, viewManagerModel);
        final OpenEntryInputBoundary openEntryInteractor = new OpenEntryInteractor(dataAccess, openEntryOutputBoundary, entryFactory);
        final OpenEntryController controller = new OpenEntryController(openEntryInteractor);
        entryListView.setOpenEntryController(controller);
        return this;
    }

    /**
     * Creates a builder that has the view entry view and associated model
     * @return Builder that has an view entry view and its model
     */
    public Builder addViewEntryView() {
        viewEntryViewModel = new ViewEntryViewModel();
        viewEntryView = new ViewEntryView(viewEntryViewModel);
        cardPanel.add(viewEntryView, viewEntryViewModel.getViewName());
        return this;
    };

    /**
     * Creates a builder that has the edit images view and associated model
     * @return Builder that has an edit images view and its model
     */
    public Builder addEditImagesView() {
        editImagesViewModel = new EditImagesViewModel();
        editImagesView = new EditImagesView(editImagesViewModel);
        cardPanel.add(editImagesView, editImagesViewModel.getViewName());
        return this;
    }

    /**
     * Creates a builder that has the update coordinates view and associated model
     * @return Builder that has an update coordinates view and its model
     */
    public Builder addUpdateCoordsView() {
        updateCoordsViewModel = new UpdateCoordsViewModel();
        updateCoordsView = new UpdateCoordsView(updateCoordsViewModel);
        cardPanel.add(updateCoordsView, updateCoordsViewModel.getViewName());
        return this;
    }

    /**
     * Creates a builder that has the update text view and associated model
     * @return Builder that has an update text view and its model
     */
    public Builder addUpdateTextView() {
        updateTextViewModel = new UpdateTextViewModel();
        updateTextView = new UpdateTextView(updateTextViewModel);
        cardPanel.add(updateTextView, updateTextViewModel.getViewName());
        return this;
    }

    /**
     * Creates a builder with all the objects needed for viewing entries to function
     * @return A Builder with all the objects needed for the view entry use case
     */
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

    /**
     * Creates a builder with all the objects needed for updating text to function
     * @return A Builder with all the objects needed for the update text use case
     */
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

    /**
     * Creates a builder with all the objects needed for editing images to function
     * @return A Builder with all the objects needed for the edit image use case
     */
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

    /**
     * Creates a builder with all the objects needed for updating coordinates to function
     * @return A Builder with all the objects needed for the update coords use case
     */
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

    /**
     * Creates the JFrame that contains the appropriate parts of the appropriate view
     * @return JFrame with the default view in it.
     */
    public JFrame build(){
        final JFrame application = new JFrame("Entries");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.add(cardPanel);

        // TODO change this back to the entry list
//        viewManagerModel.setState(viewEntryViewModel.getViewName());
        viewManagerModel.setState(entryListView.getViewName());
        viewManagerModel.firePropertyChanged();

        return application;
    }
}