package interface_adapter;


/**
 * Model for the View Manager. Its state is the name of the View which
 * is currently active. An initial state of "" is used.
 *
 * This was taken from CSC207 lab 5.
 */
public class ViewManagerModel extends ViewModel<String> {

    public ViewManagerModel() {
        super("view manager");
        this.setState("");
    }
}