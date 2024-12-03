package interface_adapter.updateText;

public class UpdateTextState {
    private String title = "";
    private String description = "";
    private String updateTextError;

    /**
     * Gets the title of the entry.
     * @return The title of the entry
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the entry
     * @param title The new title of the entry to set to
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the description of the entry.
     * @return The description of the entry
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the entry
     * @param description The new description of the entry to set to
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the update text error message.
     * @return The title of the entry
     */
    public String getUpdateTextError() {
        return updateTextError;
    }

    /**
     * Sets the error message for updating text
     * @param updateTextError The new error message to set to
     */
    public void setUpdateTextError(String updateTextError) {
        this.updateTextError = updateTextError;
    }
}
