package use_case.viewEntry;

import entity.Entry;

public class ViewEntryInteractor implements ViewEntryInputBoundary {

    private final ViewEntryOutputBoundary viewEntryPresenter;
    public final ViewEntryDataAccessInterface viewEntryDataAccess;

    public ViewEntryInteractor(ViewEntryOutputBoundary viewEntryPresenter, ViewEntryDataAccessInterface viewEntryDataAccess) {
        this.viewEntryPresenter = viewEntryPresenter;
        this.viewEntryDataAccess = viewEntryDataAccess;
    }

    @Override
    public void editImages(ViewEntryInputData viewEntryInputData) {
        Integer id = viewEntryInputData.id();

        try {
            Entry entryData = viewEntryDataAccess.getEntryData(id);
            ViewEntryOutputData outputData = new ViewEntryOutputData(entryData);
            viewEntryPresenter.prepareEditImagesView(outputData);

        } catch (Exception e) {
            viewEntryPresenter.prepareFailView("There was an issue retrieving data for entry id:" + id);
        }
    }

    @Override
    public void updateCoords(ViewEntryInputData viewEntryInputData) {
        Integer id = viewEntryInputData.id();

        try {
            Entry entryData = viewEntryDataAccess.getEntryData(id);
            ViewEntryOutputData outputData = new ViewEntryOutputData(entryData);
            viewEntryPresenter.prepareUpdateCoordsView(outputData);

        } catch (Exception e) {
            viewEntryPresenter.prepareFailView("There was an issue retrieving data for entry id:" + id);
        }
    }

    @Override
    public void editText(ViewEntryInputData viewEntryInputData) {
        Integer id = viewEntryInputData.id();

        try {
            Entry entryData = viewEntryDataAccess.getEntryData(id);
            ViewEntryOutputData outputData = new ViewEntryOutputData(entryData);
            viewEntryPresenter.prepareEditTextView(outputData);

        } catch (Exception e) {
            viewEntryPresenter.prepareFailView("There was an issue retrieving data for entry id:" + id);
        }
    }
}
