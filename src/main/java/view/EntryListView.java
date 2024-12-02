package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import interface_adapter.change_sort.ChangeSortController;
import interface_adapter.change_sort.ListState;
import interface_adapter.change_sort.ListViewModel;

import interface_adapter.open_entry.OpenEntryController;

// TODO: Needs Entry views and stuff to be added
/**
 * The view for when the user is looking at the list of entries.
 */
public class EntryListView extends JPanel implements PropertyChangeListener {
    private final String viewName = "list";

    private final ListViewModel listViewModel;
    private ChangeSortController changeSortController;
    private OpenEntryController openEntryController;

    private final HashMap<String, Integer> entries;
    private final ArrayList<JButton> buttons;
    private final ArrayList<Integer> indices;

    private final JButton ascendingButton;
    private final JButton descendingButton;

    public EntryListView(ListViewModel listViewModel) {
        this.setLayout(new GridBagLayout());
        this.listViewModel = listViewModel;
        listViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Entries");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel buttonPanel = new JPanel();
        ascendingButton = new JButton(ListViewModel.ASCENDING_BUTTON_LABEL);
        buttonPanel.add(ascendingButton);
        descendingButton = new JButton(ListViewModel.DESCENDING_BUTTON_LABEL);
        buttonPanel.add(descendingButton);

        this.entries = new HashMap<String, Integer>();
        this.buttons = new ArrayList<JButton>();
        this.indices = new ArrayList<Integer>();
        final JPanel entries = new JPanel();
        for (int i = 0; i < listViewModel.getState().getList()[0].size(); i++) {
            final JButton button = new JButton(listViewModel.getState().getList()[0].get(i));
            this.entries.put(listViewModel.getState().getList()[1].get(i), Integer.parseInt(listViewModel.getState().getList()[2].get(i)));
            entries.add(button);
            this.buttons.add(button);
            this.indices.add(i);
            final int j = i;
            button.addActionListener(
                    new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            if (e.getSource().equals(button)) {
                                openEntryController.execute(Integer.parseInt(listViewModel.getState().getList()[2].get(indices.get(buttons.indexOf(button)))));
                            }
                        }
                    }
            );
        }


        ascendingButton.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource().equals(ascendingButton)) {
                        changeSortController.execute(0);
                    }
                }
            }
        );

        descendingButton.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource().equals(descendingButton)) {
                        changeSortController.execute(1);
                    }
                }
            }
        );

        this.add(title);
        this.add(buttonPanel);
        this.add(entries);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final ListState state = (ListState) evt.getNewValue();
        int i = 0;
        for (JButton button : buttons) {
            button.setText(state.getList()[0].get(i));
            indices.set(i, state.getList()[0].size()-1-i);
            i++;
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setChangeSortController(ChangeSortController controller) {
        this.changeSortController = controller;
    }

    public void setOpenEntryController(OpenEntryController controller) {
        this.openEntryController = controller;
    }
}
