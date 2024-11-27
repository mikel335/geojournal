package use_case.updateText;

public class UpdateTextInputData {
    private final String title;
    private final String description;

    public UpdateTextInputData(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
