package interface_adapter.addImage;

import use_case.addImage.EditImagesOutputBoundary;
import use_case.addImage.EditImagesOutputData;

public class EditImagesPresenter implements EditImagesOutputBoundary {
    @Override
    public void prepareSuccessView(EditImagesOutputData outputData) {
        
    }

    @Override
    public void prepareFailView(String errorMessage) {

    }
}
