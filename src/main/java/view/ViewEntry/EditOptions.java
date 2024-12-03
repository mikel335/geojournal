package view.ViewEntry;

import interface_adapter.viewEntry.ViewEntryController;
import view.Components.Colors;
import view.Components.StyledButton;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridLayout;

public class EditOptions extends JPanel {
    private ViewEntryController viewEntryController;
    private final JButton editText;
    private final JButton changeEntryCoords;
    private final JButton editImages;
    private final JButton returnToEntries;

    public EditOptions(ViewEntryController controller) {
        this.viewEntryController = controller;
        setLayout(new GridLayout(2,2, 10, 10));
        setBackground(Colors.lightBlue);

        editText = new StyledButton("Edit Text");
        changeEntryCoords = new StyledButton("Change Geo Coordinates");
        editImages = new StyledButton("Edit Images");
        returnToEntries = new StyledButton("Return to All Entries");

        add(editText);
        add(changeEntryCoords);
        add(editImages);
        add(returnToEntries);
    }

    /**
     * Sets the controller for the view entry use case.
     * @param viewEntryController The new controller for the view entry use case
     */
    public void setViewEntryController(ViewEntryController viewEntryController) {
        this.viewEntryController = viewEntryController;

        editText.addActionListener(_ -> {
            viewEntryController.editText();
        });

        changeEntryCoords.addActionListener(_ -> {
            viewEntryController.updateCoords();
        });

        editImages.addActionListener(_ -> {
            viewEntryController.editImages();
        });

        returnToEntries.addActionListener(_ -> {
            viewEntryController.returnToHome();
        });
    }
}
