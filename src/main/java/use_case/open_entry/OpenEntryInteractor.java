package use_case.open_entry;

import entity.Entry;
import entity.EntryFactory;

/**
 * The change sort interactor.
 */
public class OpenEntryInteractor implements OpenEntryInputBoundary {
    private final OpenEntryDataAccessInterface dataAccessObject;
    private final OpenEntryOutputBoundary presenter;
    private final EntryFactory entryFactory;

    public OpenEntryInteractor(OpenEntryDataAccessInterface openEntryDataAccessInterface,
                                OpenEntryOutputBoundary openEntryOutputBoundary,
                                EntryFactory entryFactory) {
        this.dataAccessObject = openEntryDataAccessInterface;
        this.presenter = openEntryOutputBoundary;
        this.entryFactory = entryFactory;
    }

    @Override
    public void execute(OpenEntryInputData openEntryInputData) {
        final int id = openEntryInputData.getID();
        final Entry entry = dataAccessObject.getEntry(id);
        final OpenEntryOutputData openEntryOutputData = new OpenEntryOutputData(entry.getId(),
                entry.getTitle(),
                entry.getDescription(),
                entry.getLongitude(),
                entry.getLatitude(),
                entry.getDate(),
                entry.getImagePaths()
                );
        presenter.prepareSuccessView(openEntryOutputData);
    }
}
