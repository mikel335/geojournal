package view;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import interface_adapter.change_sort.ChangeSortController;
import interface_adapter.change_sort.ListState;
import interface_adapter.change_sort.ListViewModel;
import interface_adapter.createEntry.CreateEntryController;
import interface_adapter.open_entry.OpenEntryController;
import use_case.change_sort.EntryListButtonData;
import use_case.change_sort.SortMethod;
import view.Components.Colors;
import view.Components.StyledButton;

/**
 * The view for when the user is looking at the list of entries.
 */
public class EntryListView extends JPanel implements PropertyChangeListener {

    public static final Font SANS_SERIF = new Font("sans serif", Font.BOLD, 20);
    private ChangeSortController changeSortController;
    private OpenEntryController openEntryController;
    private CreateEntryController createEntryController;
    private final GridLayout gridLayout;

    private final JPanel entriesPanel;

    public EntryListView(ListViewModel listViewModel) {

        setSize(1200, 800);
        setLayout(new BorderLayout(30, 30));
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        setBackground(Colors.lightBlue);


        listViewModel.addPropertyChangeListener(this);

        final JPanel titleAndLogo = new JPanel(new BorderLayout());
        titleAndLogo.setBackground(Colors.lightBlue);

        final JButton createEntry = new StyledButton("New Entry +");
        final JLabel title = new JLabel("All GeoJournal Entries");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setVerticalAlignment(SwingConstants.TOP);
        title.setFont(new Font ("Sans serif", Font.BOLD, 40));

        final ImageIcon logo = new ImageIcon("./logo.png");
        Image resizedImage = logo.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        final JLabel logoPanel = new JLabel(resizedIcon);
        titleAndLogo.add(logoPanel, BorderLayout.WEST);
        titleAndLogo.add(title, BorderLayout.CENTER);
        titleAndLogo.add(createEntry, BorderLayout.EAST);

        gridLayout = new GridLayout(1, 1,0,0);
        entriesPanel = new JPanel(gridLayout);

        JScrollPane scrollPane = new JScrollPane(entriesPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(new EmptyBorder(0,0,0,0));

        this.populateEntryButtons(listViewModel.getState().getEntryList());

        final JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1,5, 10,0));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        buttonPanel.setPreferredSize(new Dimension(1200, 100));
        buttonPanel.setBackground(Colors.lightBlue);

        final JLabel description = new JLabel("Sort By: ");
        description.setFont(new Font("sans serif", Font.BOLD, 40));
        final JButton dateAsc = new StyledButton("Date Ascending");
        final JButton dateDesc = new StyledButton("Date Descending");
        final JButton titleAsc = new StyledButton("Title Ascending");
        final JButton titleDesc = new StyledButton("Title Descending");

        buttonPanel.add(description);
        buttonPanel.add(dateAsc);
        buttonPanel.add(dateDesc);
        buttonPanel.add(titleAsc);
        buttonPanel.add(titleDesc);

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

        createEntry.addActionListener((ActionEvent _)-> {
            createEntryController.createEntry();
        });

        buttonPanel.add(dateAsc);
        buttonPanel.add(dateDesc);
        buttonPanel.add(titleAsc);
        buttonPanel.add(titleDesc);

        this.add(titleAndLogo, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
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

    /**
     * Gets the view name of the current view.
     * @return The view name of the current view
     */
    public String getViewName() {
        return "listView";
    }

    /**
     * Sets the controller for the change sort use case.
     * @param controller The controller to use for the change sort use case
     */
    public void setChangeSortController(ChangeSortController controller) {
        this.changeSortController = controller;
        controller.execute(SortMethod.DATE_ASCENDING);
    }

    /**
     * Sets the controller for the open entry use case.
     * @param controller The controller to use for the open entry use case
     */
    public void setOpenEntryController(OpenEntryController controller) {
        this.openEntryController = controller;
    }

    private void populateEntryButtons(ArrayList<EntryListButtonData > entryList) {
        entriesPanel.removeAll();

        if (entryList != null ) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("'Created': MM, dd, yyyy 'at' HH:mm");
            for (EntryListButtonData entryListButtonData : entryList) {

                final JPanel entryPanel = new JPanel();
                entryPanel.setLayout(new BorderLayout());
                entryPanel.setBackground(Colors.lightBlue);
                entryPanel.setPreferredSize(new Dimension(-1,100));
                entryPanel.setBorder(BorderFactory.createMatteBorder(4,0,5,0, Colors.lightBlue));

                final JButton button = new StyledButton(entryListButtonData.getTitle());
                button.setFont(SANS_SERIF);
                button.setPreferredSize(new Dimension(400, 80));

                Date entryDate = new Date(Long.parseLong(entryListButtonData.getDate()));
                final JLabel label = new JLabel(dateFormat.format(entryDate));
                label.setFont(SANS_SERIF);

                entryPanel.add(button, BorderLayout.WEST);
                entryPanel.add(label, BorderLayout.EAST);

                entriesPanel.add(entryPanel);
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

            int rows = Math.ceilDiv(entryList.size(), 1);
            this.gridLayout.setRows(rows);
            this.gridLayout.setColumns(1);
        }
    }

    /**
     * Sets the controller for the create entry use case.
     * @param createEntryController The controller to use for the create entry use case
     */
    public void addCreateEntryController(CreateEntryController createEntryController) {
        this.createEntryController = createEntryController;
    }
}
