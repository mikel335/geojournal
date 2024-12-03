package interface_adapter.updateText;

import use_case.updateText.UpdateTextInputBoundary;
import use_case.updateText.UpdateTextInputData;


public class UpdateTextController {
    private final UpdateTextInputBoundary updateTextInteractor;

    public UpdateTextController(UpdateTextInputBoundary updateTextInteractor) {
        this.updateTextInteractor = updateTextInteractor;
    }

    /**
     * Executes the update text use case.
     * @param title The new title to update to
     * @param description The new description to update to
     */
    public void execute(String title, String description) {
        final UpdateTextInputData updateTextInput = new UpdateTextInputData(title, description);
        updateTextInteractor.execute(updateTextInput);
    }

    /**
     * Cancels the current text update and returns to the entry view.
     */
    public void cancelUpdate() {
        updateTextInteractor.cancelUpdate();
    }
}
