package interface_adapter.updateText;

import use_case.updateText.UpdateTextInputBoundary;
import use_case.updateText.UpdateTextInputData;
import use_case.viewEntry.ViewEntryInputBoundary;
import use_case.viewEntry.ViewEntryInputData;


public class UpdateTextController {
    private final UpdateTextInputBoundary updateTextInteractor;

    public UpdateTextController(UpdateTextInputBoundary updateTextInteractor) {
        this.updateTextInteractor = updateTextInteractor;
    }

    public void execute(String title, String description) {
        final UpdateTextInputData updateTextInput = new UpdateTextInputData(title, description);
        updateTextInteractor.execute(updateTextInput);
    }
}
