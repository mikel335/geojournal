package use_case.updateText;

public class UpdateTextOutputData {
    private final String title;
    private final String description;

    UpdateTextOutputData (String title, String description) {
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
