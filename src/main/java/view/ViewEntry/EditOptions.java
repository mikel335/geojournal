package view.ViewEntry;

import interface_adapter.viewEntry.ViewEntryController;

import javax.swing.*;
import java.awt.*;

public class EditOptions extends JPanel {
    private ViewEntryController viewEntryController;
    private final JButton editText;
    private final JButton changeEntryCoords;
    private final JButton editImages;
    private final JButton returnToEntries;

    public EditOptions(ViewEntryController controller) {
        this.viewEntryController = controller;
        setLayout(new GridLayout(3,1));

        editText = new JButton("Edit Text");
        changeEntryCoords = new JButton("Change Geo Coordinates");
        editImages = new JButton("Edit Images");
        returnToEntries = new JButton("Return to All Entries");

        add(editText);
        add(changeEntryCoords);
        add(editImages);
        add(returnToEntries);
    }

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
