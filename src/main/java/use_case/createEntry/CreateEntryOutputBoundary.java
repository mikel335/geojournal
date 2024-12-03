package use_case.createEntry;

public interface CreateEntryOutputBoundary {

    /**
     * Present the success screen for the create entry use case
     * @param output New entry data
     */
    void prepareSuccessView(CreateEntryOutputData output);

    /**
     * Present a failed state screen
     * @param errorMessage error message to display
     */
    void prepareFailView(String errorMessage);
}
