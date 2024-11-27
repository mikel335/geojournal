package interface_adapter.editImages;

import use_case.editImages.EditImagesOutputBoundary;
import use_case.editImages.EditImagesOutputData;

public class EditImagesPresenter implements EditImagesOutputBoundary {
    @Override
    public void prepareSuccessView(EditImagesOutputData outputData) {

    }

    @Override
    public void prepareFailView(String errorMessage) {

    }
}
