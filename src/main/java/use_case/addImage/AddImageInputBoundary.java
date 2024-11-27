package use_case.addImage;

public interface AddImageInputBoundary {

    /**
     * Execute the add image use case
     * @param addImageInputData new image data
     */
    void execute(AddImageInputData addImageInputData);
}
