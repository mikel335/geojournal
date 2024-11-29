package app;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import data_access.DataAccessObject;
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
import use_case.change_sort.ChangeSortInputBoundary;
import use_case.change_sort.ChangeSortInteractor;
import use_case.change_sort.ChangeSortOutputBoundary;
import use_case.open_entry.OpenEntryInputBoundary;
import use_case.open_entry.OpenEntryInteractor;
import use_case.open_entry.OpenEntryOutputBoundary;
import view.EntryListView;
import view.MainEntryView;
import view.ViewManager;

/**
 * The Builder puts the CA architecture together.
 */
public class Builder{
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    private final EntryFactory entryFactory = new EntryFactory();
    private final EntryListFactory entryListFactory = new EntryListFactory();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    private final DataAccessObject dao = new DataAccessObject();
    private final EntryDataAccess edao = new EntryDataAccess();

    private EntryListView entryListView;
    private ListViewModel listViewModel;
    private ViewEntryViewModel viewEntryViewModel;
    private MainEntryView mainEntryView;

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

    public JFrame build(){
        final JFrame application = new JFrame("Entries");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.add(cardPanel);

        viewManagerModel.setState(entryListView.getViewName());
        viewManagerModel.firePropertyChanged();

        return application;
    }
}