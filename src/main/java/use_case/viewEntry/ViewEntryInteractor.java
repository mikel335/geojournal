package use_case.viewEntry;

import entity.Entry;

public class ViewEntryInteractor implements ViewEntryInputBoundary {

    private final ViewEntryOutputBoundary viewEntryPresenter;
    private final ViewEntryDataAccessInterface viewEntryDataAccess;

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
            viewEntryPresenter.prepareFailView("There was an issue retrieving data for the current entry to edit images");
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
            viewEntryPresenter.prepareFailView("There was an issue retrieving data for the current entry to update coords");
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
            viewEntryPresenter.prepareFailView("There was an issue retrieving data for the current entry to edit text");
        }
    }

    public void viewEntry() {
        try {
            Entry entryData = viewEntryDataAccess.getCurrentEntry();

            ViewEntryOutputData outputData = new ViewEntryOutputData(
                    entryData.getImagePaths(),
                    entryData.getLatitude(),
                    entryData.getLongitude(),
                    entryData.getTitle(),
                    entryData.getDescription());
            viewEntryPresenter.prepareViewEntryView(outputData);

        } catch (Exception e) {
            viewEntryPresenter.prepareFailView("There was an issue retrieving data for the current entry" + e.getMessage());
        }
    }
}
