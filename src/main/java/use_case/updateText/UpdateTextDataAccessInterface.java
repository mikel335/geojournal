package use_case.updateText;

public interface UpdateTextDataAccessInterface {

    /**
     * Changes the entry title
     * @param title The new title
     */

    void setTitle(String title);

    /**
     * Fetches the current title
     * @return the current title
     */
    String getTitle();

    /**
     * Changes the entry description
     * @param description The new description
     */
    void setDescription(String description);

    /**
     * Fetches the current description
     * @return the current description
     */
    String getDescription();
}
