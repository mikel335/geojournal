package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import interface_adapter.change_sort.ChangeSortController;
import interface_adapter.change_sort.ListState;
import interface_adapter.change_sort.ListViewModel;

import interface_adapter.open_entry.OpenEntryController;
import use_case.change_sort.EntryListButtonData;
import use_case.change_sort.SortMethod;

/**
 * The view for when the user is looking at the list of entries.
 */
public class EntryListView extends JPanel implements PropertyChangeListener {

    private ChangeSortController changeSortController;
    private OpenEntryController openEntryController;

    private final JPanel entriesPanel;

    public EntryListView(ListViewModel listViewModel) {
        this.setLayout(new BorderLayout());
        listViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Entries");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.entriesPanel = new JPanel();
        this.populateEntryButtons(listViewModel.getState().getEntryList());

        final JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        final JButton dateAsc = new JButton("Date Ascending");
        final JButton dateDesc = new JButton("Date Descending");
        final JButton titleAsc = new JButton("Title Ascending");
        final JButton titleDesc = new JButton("Title Descending");

        dateAsc.addActionListener((ActionEvent _)-> {
            changeSortController.execute(SortMethod.DATE_ASCENDING);
        });

        dateDesc.addActionListener((ActionEvent _)-> {
            changeSortController.execute(SortMethod.DATE_DESCENDING);
        });

        titleAsc.addActionListener((ActionEvent _)-> {
            changeSortController.execute(SortMethod.TITLE_ASCENDING);
        });

        titleDesc.addActionListener((ActionEvent _)-> {
            changeSortController.execute(SortMethod.TITLE_DESCENDING);
        });

        buttonPanel.add(dateAsc);
        buttonPanel.add(dateDesc);
        buttonPanel.add(titleAsc);
        buttonPanel.add(titleDesc);

        this.add(title, BorderLayout.NORTH);
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.add(entriesPanel, BorderLayout.CENTER);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getNewValue() instanceof ListState state) {
            if(state.getErrorMessage() != null) {
                JOptionPane.showMessageDialog(null, state.getErrorMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                this.populateEntryButtons(state.getEntryList());
            }
        }

    }

    public String getViewName() {
        return "list";
    }

    public void setChangeSortController(ChangeSortController controller) {
        this.changeSortController = controller;
        controller.execute(SortMethod.DATE_ASCENDING);
    }

    public void setOpenEntryController(OpenEntryController controller) {
        this.openEntryController = controller;
    }

    private void populateEntryButtons(ArrayList<EntryListButtonData > entryList) {
        entriesPanel.removeAll();
        entriesPanel.validate();
        entriesPanel.repaint();

        if (entryList != null ) {
            for (EntryListButtonData entryListButtonData : entryList) {
                final JButton button = new JButton(entryListButtonData.getTitle());
                entriesPanel.add(button);
                button.addActionListener(
                        (ActionEvent e)-> {
                            if (e.getSource().equals(button)) {
                                openEntryController.execute(entryListButtonData.getId());
                            }
                        }
                );
            }
            entriesPanel.revalidate();
            entriesPanel.repaint();
        }
    }
}
