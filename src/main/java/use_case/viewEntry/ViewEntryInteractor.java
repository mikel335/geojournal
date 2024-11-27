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
    public void editImages() {

        try {
            Entry entryData = viewEntryDataAccess.getCurrentEntry();
            ViewEntryOutputData outputData = new ViewEntryOutputData(
                    entryData.getImagePaths(),
                    entryData.getLatitude(),
                    entryData.getLongitude(),
                    entryData.getTitle(),
                    entryData.getDescription()

            );
            viewEntryPresenter.prepareEditImagesView(outputData);

        } catch (Exception e) {
            viewEntryPresenter.prepareFailView("There was an issue retrieving data for the current entry");
        }
    }

    @Override
    public void updateCoords() {

        try {
            Entry entryData = viewEntryDataAccess.getCurrentEntry();
            ViewEntryOutputData outputData = new ViewEntryOutputData(
                    entryData.getImagePaths(),
                    entryData.getLatitude(),
                    entryData.getLongitude(),
                    entryData.getTitle(),
                    entryData.getDescription());
            viewEntryPresenter.prepareUpdateCoordsView(outputData);

        } catch (Exception e) {
            viewEntryPresenter.prepareFailView("There was an issue retrieving data for the current entry");
        }
    }

    @Override
    public void editText() {

        try {
            Entry entryData = viewEntryDataAccess.getCurrentEntry();
            ViewEntryOutputData outputData = new ViewEntryOutputData(
                    entryData.getImagePaths(),
                    entryData.getLatitude(),
                    entryData.getLongitude(),
                    entryData.getTitle(),
                    entryData.getDescription());
            viewEntryPresenter.prepareEditTextView(outputData);

        } catch (Exception e) {
            viewEntryPresenter.prepareFailView("There was an issue retrieving data for the current entry");
        }
    }
}
