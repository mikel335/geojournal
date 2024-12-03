package use_case.createEntry;

import entity.Entry;

public class CreateEntryInteractor implements CreateEntryInputBoundary{

    private CreateEntryDataAccessInterface createEntryDataAccessInterface;
    private CreateEntryOutputBoundary createEntryPresenter;

    public CreateEntryInteractor(
            CreateEntryDataAccessInterface createEntryDataAccessInterface,
        CreateEntryOutputBoundary createEntryPresenter) {
        this.createEntryDataAccessInterface = createEntryDataAccessInterface;
        this.createEntryPresenter = createEntryPresenter;
    }

    @Override
    public void execute() {
        try {
            Entry newEntry = createEntryDataAccessInterface.createEntry();
            CreateEntryOutputData output = new CreateEntryOutputData(
                    newEntry.getImagePaths(),
                    newEntry.getLatitude(),
                    newEntry.getLongitude(),
                    newEntry.getTitle(),
                    newEntry.getDescription()
            );

            createEntryPresenter.prepareSuccessView(output);

        } catch (Exception e) {
            createEntryPresenter.prepareFailView("There was an issue creating the entry");
        }
    }
}
