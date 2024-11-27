package interface_adapter.viewEntry;

import interface_adapter.ViewManagerModel;
import interface_adapter.addImage.EditImagesViewModel;
import interface_adapter.updateCoords.UpdateCoordsViewModel;
import interface_adapter.updateText.UpdateTextViewModel;
import use_case.viewEntry.ViewEntryOutputBoundary;
import use_case.viewEntry.ViewEntryOutputData;

public class ViewEntryPresenter implements ViewEntryOutputBoundary {

    private final ViewEntryViewModel viewEntryView;

    // All potential
    private final EditImagesViewModel addImageView;
    private final UpdateCoordsViewModel updateCoordsView;
    private final UpdateTextViewModel updateTextView;

    private final ViewManagerModel viewManagerModel;


    public ViewEntryPresenter(ViewEntryViewModel viewEntryView,
                              EditImagesViewModel addImageView,
                              UpdateCoordsViewModel updateCoordsView,
                              UpdateTextViewModel updateTextViewModel,
                              ViewManagerModel viewManagerModel) {
        this.viewEntryView = viewEntryView;
        this.addImageView = addImageView;
        this.updateCoordsView = updateCoordsView;
        this.updateTextView = updateTextViewModel;
        this.viewManagerModel = viewManagerModel;
    }


    @Override
    public void prepareAddImageView(ViewEntryOutputData outputData) {

    }
    

    @Override
    public void prepareUpdateCoordsView(ViewEntryOutputData outputData) {

    }

    @Override
    public void prepare(ViewEntryOutputData outputData) {

    }

    // On failure, stay on the edit coords view and display an error
    @Override
    public void prepareFailView(String errorMessage) {

    }



}
