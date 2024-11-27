package use_case.editImages;

public class AddImageInputData {
    private final String imagePath;

    public AddImageInputData(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImagePath() {
        return imagePath;
    }
}
