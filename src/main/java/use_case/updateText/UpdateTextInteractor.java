package use_case.updateText;

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

                // Update UI with new data
                final UpdateTextOutputData outputData = new UpdateTextOutputData(
                        updateTextDataAccess.getTitle(),
                        updateTextDataAccess.getDescription());
                updateTextPresenter.prepareSuccessView(outputData);

        } catch (Exception e) {
            // Handle failed update
            updateTextPresenter.prepareFailView(e.getMessage());
        }
    }
}
