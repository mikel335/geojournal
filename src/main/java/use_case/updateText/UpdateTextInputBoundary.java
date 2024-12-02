package use_case.updateText;

public interface UpdateTextInputBoundary {

    /**
     * Executes the change text use case
     * @param updateTextInputData new text data
     */
    void execute(UpdateTextInputData updateTextInputData);

    /**
     * Cancels the update and returns to view entry
     */
    void cancelUpdate();
}
