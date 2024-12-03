package use_case.updateText;

import entity.Entry;

public interface UpdateTextDataAccessInterface {

    /**
     * Changes the entry title
     * @param title The new title
     */
    void setTitle(String title);

    /**
     * Changes the entry description
     * @param description The new description
     */
    void setDescription(String description);

    /**
     * Gets the entry whose text are being modified.
     * @return The entry whose text is being modified
     */
    Entry getCurrentEntry();
}
