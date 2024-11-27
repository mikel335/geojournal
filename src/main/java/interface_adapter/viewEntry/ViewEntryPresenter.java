package interface_adapter.viewEntry;

import interface_adapter.ViewManagerModel;
import interface_adapter.addImage.AddImageViewModel;
import interface_adapter.updateCoords.UpdateCoordsViewModel;
import use_case.updateCoords.UpdateCoordsOutputData;
import use_case.viewEntry.ViewEntryOutputBoundary;
import use_case.viewEntry.ViewEntryOutputData;

public class ViewEntryPresenter implements ViewEntryOutputBoundary {

    private final ViewEntryViewModel updateCoordsView;

    // All potential
    private final AddImageViewModel addImageView;
    private final DeleteImageViewModel deleteImageView;
    private final UpdateCoordsViewModel updateCoordsViewModel;
    private final UpdateTextViewModel updateTextViewModel;

    private final ViewManagerModel viewManagerModel;


    public ViewEntryPresenter(ViewEntryViewModel updateCoordsView,
                              AddImageViewModel addImageView,
                              DeleteImageViewModel deleteImageView,
                              UpdateTextViewModel updateTextViewModel,
                              ViewManagerModel viewManagerModel) {
        this.updateCoordsView = updateCoordsView;
        this.addImageView = addImageView;
        this.deleteImageView = deleteImageView;

        this.viewManagerModel = viewManagerModel;
    }


    @Override
    public void prepareAddImageView(ViewEntryOutputData outputData) {

    }

    @Override
    public void prepareDeleteImageView(ViewEntryOutputData outputData) {

    }

    @Override
    public void prepare(ViewEntryOutputData outputData) {

    }

    // On failure, stay on the edit coords view and display an error
    @Override
    public void prepareFailView(String errorMessage) {

    }



}
