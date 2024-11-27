package interface_adapter.updateText;


import use_case.updateText.UpdateTextOutputBoundary;
import use_case.updateText.UpdateTextOutputData;

public class UpdateTextPresenter implements UpdateTextOutputBoundary {

    @Override
    public void prepareSuccessView(UpdateTextOutputData outputData) {
        
    }

    @Override
    public void prepareFailView(String errorMessage) {

    }
}
