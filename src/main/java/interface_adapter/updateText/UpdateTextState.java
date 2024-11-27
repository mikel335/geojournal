package interface_adapter.updateText;

public class UpdateTextState {
    private String title = "";
    private String description = "";
    private String updateTextError;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUpdateTextError() {
        return updateTextError;
    }

    public void setUpdateTextError(String updateTextError) {
        this.updateTextError = updateTextError;
    }
}
