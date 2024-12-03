package view.UpdateCoords;

import interface_adapter.updateCoords.UpdateCoordsController;
import interface_adapter.updateCoords.UpdateCoordsState;
import interface_adapter.updateCoords.UpdateCoordsViewModel;
import view.Components.Colors;
import view.Components.StyledButton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;


public class UpdateCoordsView extends JPanel implements ActionListener, PropertyChangeListener {
    private final UpdateCoordsViewModel viewModel;
    private UpdateCoordsController updateCoordsController;

    private final CoordEditBoxes coordEditBoxes;
    final JButton saveButton = new StyledButton("Save");
    final JButton cancelButton = new StyledButton("Cancel");

    public UpdateCoordsView(UpdateCoordsViewModel updateCoordsViewModel) {
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        setBackground(Colors.lightBlue);
        this.viewModel = updateCoordsViewModel;
        this.viewModel.addPropertyChangeListener(this);

        coordEditBoxes = new CoordEditBoxes(
                String.valueOf(viewModel.getState().getLatitude()),
                String.valueOf(viewModel.getState().getLongitude())
        );

        JPanel buttonPanel1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel1.setBackground(Colors.lightBlue);

        // configuring buttons
        saveButton.setSize(150, 75);
        saveButton.setForeground(Color.GREEN.darker());
        saveButton.addActionListener(this);

        cancelButton.setSize(150, 75);
        cancelButton.setForeground(Color.RED);
        cancelButton.addActionListener(this);

        // Setting up the view
        buttonPanel1.add(saveButton, BorderLayout.EAST);
        buttonPanel1.add(cancelButton, BorderLayout.WEST);

        setLayout(new BorderLayout());
        add(coordEditBoxes, BorderLayout.NORTH); // Text area view at the top
        add(buttonPanel1, BorderLayout.SOUTH); // Button panel at the bottom (aligned right)
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveButton) {
            updateCoordsController.execute(
                    coordEditBoxes.getLatitudeText(),
                    coordEditBoxes.getLongitudeText()
            );
        }

        else if (e.getSource() == cancelButton) {
            updateCoordsController.execute(
                    String.valueOf(viewModel.getState().getLatitude()),
                    String.valueOf(viewModel.getState().getLongitude())
            );
        }
    }

    /**
     * Sets the controller for the update coordinates use case.
     * @param updateCoordsController The new controller to set to
     */
    public void setUpdateCoordsController(UpdateCoordsController updateCoordsController) {
        this.updateCoordsController = updateCoordsController;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getNewValue() instanceof UpdateCoordsState newState) {
            if (newState.getUpdateCoordsError() != null) {
                JOptionPane.showMessageDialog(
                        this,
                        newState.getUpdateCoordsError(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
            else {
                this.coordEditBoxes.setLatitudeText(String.valueOf(viewModel.getState().getLatitude()));
                this.coordEditBoxes.setLongitudeText(String.valueOf(viewModel.getState().getLongitude()));
            }
        }
    }
}

