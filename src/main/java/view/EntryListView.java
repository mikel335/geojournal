package view;

import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapter.change_sort.ChangeSortController;

// TODO: Needs Entry views and stuff to be added
/**
 * The view for when the user is looking at the list of entries.
 */
public class EntryListView extends JFrame implements PropertyChangeListener {
    private final String viewName = "list";
    private ChangeSortController changeSortController;

    private final JLabel sortMethod;

    public EntryListView() {
        final JLabel title = new JLabel("Entries");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
    }
}
