package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.html.ListView;

import interface_adapter.change_sort.ChangeSortController;
import interface_adapter.change_sort.ListState;
import interface_adapter.change_sort.ListViewModel;

import interface_adapter.change_sort.ChangeSortController;

// TODO: Needs Entry views and stuff to be added
/**
 * The view for when the user is looking at the list of entries.
 */
public class EntryListView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "list";

    private final ListViewModel listViewModel;
    private ChangeSortController changeSortController;

    private final ArrayList<JLabel> entries;

    private final JButton ascendingButton;
    private final JButton descendingButton;

    public EntryListView(ListViewModel listViewModel) {
        this.listViewModel = listViewModel;
        listViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Entries");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel buttons = new JPanel();
        ascendingButton = new JButton(ListViewModel.ASCENDING_BUTTON_LABEL);
        buttons.add(ascendingButton);
        descendingButton = new JButton(ListViewModel.DESCENDING_BUTTON_LABEL);
        buttons.add(descendingButton);

        this.entries = new ArrayList<JLabel>();
        final JPanel entries = new JPanel();
        for (String titles : listViewModel.getState().getList()[0]) {
            final JLabel label = new JLabel(titles);
            this.entries.add(label);
            entries.add(label);
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
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(buttons);
        this.add(entries);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showMessageDialog(this, "Cancel not implemented yet.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final ListState state = (ListState) evt.getNewValue();
        System.out.println(state.toString());
        System.out.println(state.getList()[0].size());
        for (int i = 0; i < state.getList()[0].size(); i++) {
            entries.get(i).setText(state.getList()[0].get(i));
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setChangeSortController(ChangeSortController controller) {
        this.changeSortController = controller;
    }
}
