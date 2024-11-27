package interface_adapter.updateCoords;

import interface_adapter.ViewManagerModel;
import interface_adapter.editImages.EditImagesViewModel;

import use_case.updateCoords.UpdateCoordsOutputBoundary;
import use_case.updateCoords.UpdateCoordsOutputData;

public class UpdateCoordsPresenter implements UpdateCoordsOutputBoundary {

    private final UpdateCoordsViewModel updateCoordsView;
    private final EditImagesViewModel updateImageView;
    private final ViewManagerModel viewManagerModel;


    public UpdateCoordsPresenter(UpdateCoordsViewModel updateCoordsView,
                                 EditImagesViewModel addImageView,
                                 ViewManagerModel viewManagerModel) {
        this.updateCoordsView = updateCoordsView;
        this.updateImageView = addImageView;
        this.viewManagerModel = viewManagerModel;
    }

    // On success, switch to the view entry view
    @Override
    public void prepareSuccessView(UpdateCoordsOutputData outputData) {

    }

    // On failure, stay on the edit coords view and display an error
    @Override
    public void prepareFailView(String errorMessage) {
        final UpdateCoordsState updateCoordsState = updateCoordsView.getState();
        updateCoordsState.setUpdateCoordsError(errorMessage);
        updateCoordsView.firePropertyChanged("updateCoordsError");
    }



}
