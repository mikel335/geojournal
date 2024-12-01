package viewWithCA.UpdateCoords;

import interface_adapter.updateCoords.UpdateCoordsController;
import interface_adapter.updateCoords.UpdateCoordsViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class UpdateCoordsView extends JPanel implements ActionListener, PropertyChangeListener {
    private final UpdateCoordsViewModel viewModel;
    private UpdateCoordsController updateCoordsController;

    private final CoordEditBoxes coordEditBoxes;
    final JButton saveButton = new JButton("Save");
    final JButton cancelButton = new JButton("Cancel");

    public UpdateCoordsView(UpdateCoordsViewModel updateCoordsViewModel) {
        this.viewModel = updateCoordsViewModel;
        this.viewModel.addPropertyChangeListener(this);

        coordEditBoxes = new CoordEditBoxes(
                String.valueOf(viewModel.getState().getLatitude()),
                String.valueOf(viewModel.getState().getLongitude())
        );

        JPanel buttonPanel1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

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

    public void setUpdateCoordsController(UpdateCoordsController updateCoordsController) {
        this.updateCoordsController = updateCoordsController;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.coordEditBoxes.setLatitudeText(String.valueOf(viewModel.getState().getLatitude()));
        this.coordEditBoxes.setLongitudeText(String.valueOf(viewModel.getState().getLongitude()));
    }
}

