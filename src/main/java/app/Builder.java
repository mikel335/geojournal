package app;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import data_access.EntryDataAccess;
import data_access.WeatherDataAccess;
import interface_adapter.ViewManagerModel;
import interface_adapter.change_sort.ChangeSortController;
import interface_adapter.change_sort.ChangeSortPresenter;
import interface_adapter.change_sort.ListViewModel;
import interface_adapter.createEntry.CreateEntryController;
import interface_adapter.createEntry.CreateEntryPresenter;
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
import interface_adapter.weather.WeatherController;
import interface_adapter.weather.WeatherPresenter;
import interface_adapter.weather.WeatherViewModel;
import use_case.change_sort.ChangeSortInputBoundary;
import use_case.change_sort.ChangeSortInteractor;
import use_case.change_sort.ChangeSortOutputBoundary;
import use_case.createEntry.CreateEntryInputBoundary;
import use_case.createEntry.CreateEntryInteractor;
import use_case.createEntry.CreateEntryOutputBoundary;
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
import use_case.weathercheck.WeatherInputBoundary;
import use_case.weathercheck.WeatherInteractor;
import use_case.weathercheck.WeatherOutputBoundary;
import view.EntryListView;
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

    // View Manager to manage which view to display
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    // Filesystem storage access
    private final EntryDataAccess dataAccess = new EntryDataAccess();

    // Weather API data
    private final WeatherDataAccess weatherDataAccess = new WeatherDataAccess();

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

    private WeatherViewModel weatherViewModel;

    public Builder(){
        cardPanel.setLayout(cardLayout);
    }

    /**
     * Adds the entry list view.
     * @return A Builder with the EntryListView initialized
     */
    public Builder addEntryListView(){
        listViewModel = new ListViewModel();
        entryListView = new EntryListView(listViewModel);
        viewEntryViewModel = new ViewEntryViewModel();
        cardPanel.add(entryListView, entryListView.getViewName());
        return this;
    }

    /**
     * Adds the weather view model.
     * @return A Builder with WeatherViewModel initialized
     */
    public Builder addWeatherViewModel() {
        weatherViewModel = new WeatherViewModel();
        return this;
    }

    /**
     * Adds the entry view.
     * @return A Builder with the ViewEntryView initialized
     */
    public Builder addViewEntryView() {
        viewEntryViewModel = new ViewEntryViewModel();
        viewEntryView = new ViewEntryView(viewEntryViewModel, weatherViewModel);
        cardPanel.add(viewEntryView, viewEntryViewModel.getViewName());
        return this;
    }

    /**
     * Adds the edit images view.
     * @return A Builder with EditImagesView initialized
     */
    public Builder addEditImagesView() {
        editImagesViewModel = new EditImagesViewModel();
        editImagesView = new EditImagesView(editImagesViewModel);
        cardPanel.add(editImagesView, editImagesViewModel.getViewName());
        return this;
    }

    /**
     * Adds the update coords view.
     * @return A Builder with the UpdateCoordsView initialized
     */
    public Builder addUpdateCoordsView() {
        updateCoordsViewModel = new UpdateCoordsViewModel();
        updateCoordsView = new UpdateCoordsView(updateCoordsViewModel);
        cardPanel.add(updateCoordsView, updateCoordsViewModel.getViewName());
        return this;
    }

    /**
     * Adds the update text view.
     * @return A Builder with the UpdateTextView initialized
     */
    public Builder addUpdateTextView() {
        updateTextViewModel = new UpdateTextViewModel();
        updateTextView = new UpdateTextView(updateTextViewModel);
        cardPanel.add(updateTextView, updateTextViewModel.getViewName());
        return this;
    }

    /**
     * Adds the change sort use case.
     * @return A Builder with the change sort use case's associated parts initialized
     */
    public Builder addChangeSortUseCase(){
        final ChangeSortOutputBoundary changeSortOutputBoundary = new ChangeSortPresenter(viewManagerModel, listViewModel);
        final ChangeSortInputBoundary changeSortInteractor = new ChangeSortInteractor(
                dataAccess ,changeSortOutputBoundary);
        final ChangeSortController controller = new ChangeSortController(changeSortInteractor);
        entryListView.setChangeSortController(controller);
        return this;
    }

    /**
     * Adds the open entry use case.
     * @return A Builder with the open entry use case's associated parts initialized
     */
    public Builder addOpenEntryUseCase(){
        final OpenEntryOutputBoundary openEntryOutputBoundary = new OpenEntryPresenter(viewEntryViewModel, viewManagerModel);
        final OpenEntryInputBoundary openEntryInteractor = new OpenEntryInteractor(dataAccess, openEntryOutputBoundary);
        final OpenEntryController controller = new OpenEntryController(openEntryInteractor);
        entryListView.setOpenEntryController(controller);
        return this;
    }

    /**
     * Adds the check weather use case.
     * @return A Builder with the check weather use case's associated parts initialized
     */
    public Builder addWeatherUseCase() {
        final WeatherOutputBoundary weatherPresenter = new WeatherPresenter(
                weatherViewModel
        );

        final WeatherInputBoundary weatherInteractor = new WeatherInteractor(
                weatherDataAccess,
                weatherPresenter
        );

        final WeatherController controller = new WeatherController(weatherInteractor);
        viewEntryView.addWeatherController(controller);

        return this;
    }

    /**
     * Adds the create entry use case.
     * @return A Builder with the create entry use case's associated parts initialized
     */
    public Builder addCreateEntryUseCase() {
        final CreateEntryOutputBoundary createEntryPresenter = new CreateEntryPresenter(
                    viewManagerModel,
                    viewEntryViewModel,
                    listViewModel
                );
        final CreateEntryInputBoundary createEntryInteractor = new CreateEntryInteractor(
                dataAccess,
                createEntryPresenter
        );

        final CreateEntryController controller = new CreateEntryController(createEntryInteractor);
        entryListView.addCreateEntryController(controller);
        return this;
    }

    /**
     * Adds the view entry use case.
     * @return A Builder with the view entry use case's associated parts initialized
     */
    public Builder addViewEntryUseCase() {
        final ViewEntryOutputBoundary viewEntryPresenter = new ViewEntryPresenter(
                viewEntryViewModel,
                editImagesViewModel,
                updateCoordsViewModel,
                updateTextViewModel,
                viewManagerModel,
                listViewModel);

        final ViewEntryInputBoundary viewEntryInteractor = new ViewEntryInteractor(
                viewEntryPresenter,
                dataAccess
        );

        final ViewEntryController controller = new ViewEntryController(viewEntryInteractor);
        viewEntryView.addViewEntryController(controller);
        return this;
    }

    /**
     * Adds the update text use case.
     * @return A Builder with the update text use case's associated parts initialized
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
     * Adds the add/edit images use case.
     * @return A Builder with the add/edit images use case's associated parts initialized
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
     * Adds the change update coordinates case.
     * @return A Builder with the update coordinates use case's associated parts initialized
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
     * Creates a JFrame with the default view open.
     * @return A JFrame with the default view (entry list) open
     */
    public JFrame build(){
        final JFrame application = new JFrame("Entries");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.add(cardPanel);

        viewManagerModel.setState(entryListView.getViewName());
        viewManagerModel.firePropertyChanged();

        return application;
    }
}