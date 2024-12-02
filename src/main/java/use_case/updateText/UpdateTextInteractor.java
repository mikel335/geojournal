package use_case.updateText;

import entity.Entry;

public class UpdateTextInteractor implements UpdateTextInputBoundary {
    private final UpdateTextOutputBoundary updateTextPresenter;
    private final UpdateTextDataAccessInterface updateTextDataAccess;

    public UpdateTextInteractor(UpdateTextOutputBoundary updateTextPresenter,
                                UpdateTextDataAccessInterface updateTextDataAccess) {
        this.updateTextPresenter = updateTextPresenter;
        this.updateTextDataAccess = updateTextDataAccess;
    }

    @Override
    public void execute(UpdateTextInputData updateTextInputData) {
        try {
                final String title = updateTextInputData.title();
                final String description = updateTextInputData.description();

                // Change text in the backend
                updateTextDataAccess.setTitle(title);
                updateTextDataAccess.setDescription(description);

                // Grab the new text and display it on the UI
                Entry updatedEntry = updateTextDataAccess.getCurrentEntry();
                final UpdateTextOutputData outputData = new UpdateTextOutputData(
                        updatedEntry.getTitle(),
                        updatedEntry.getDescription());
                updateTextPresenter.prepareSuccessView(outputData);

        } catch (Exception e) {
            // Handle failed update
            updateTextPresenter.prepareFailView(e.getMessage());
        }
    }

    @Override
    public void cancelUpdate() {
        try {
            //Just run the success view (return to view entry) with no change to the text
            Entry currentEntry = updateTextDataAccess.getCurrentEntry();
            final UpdateTextOutputData outputData = new UpdateTextOutputData(
                    currentEntry.getTitle(),
                    currentEntry.getDescription());
            updateTextPresenter.prepareSuccessView(outputData);

        } catch (Exception e) {
            // Handle failed update
            updateTextPresenter.prepareFailView(e.getMessage());
        }
    }
}
