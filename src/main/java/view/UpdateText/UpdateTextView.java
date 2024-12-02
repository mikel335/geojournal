package view.UpdateText;

import interface_adapter.updateText.UpdateTextController;
import interface_adapter.updateText.UpdateTextState;
import interface_adapter.updateText.UpdateTextViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class UpdateTextView extends JPanel implements ActionListener, PropertyChangeListener {
    private final UpdateTextViewModel viewModel;
    private UpdateTextController updateTextController;

    private final TitleDescEditBoxes titleDescEditBoxes;
    final JButton saveButton = new JButton("Save");
    final JButton cancelButton = new JButton("Cancel");

    public UpdateTextView(UpdateTextViewModel updateTextModel) {
        this.viewModel = updateTextModel;
        this.viewModel.addPropertyChangeListener(this);

        titleDescEditBoxes = new TitleDescEditBoxes(
                viewModel.getState().getTitle(),
                viewModel.getState().getDescription()
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
        add(titleDescEditBoxes, BorderLayout.NORTH); // Text area view at the top
        add(buttonPanel1, BorderLayout.CENTER); // Button panel at the bottom (aligned right)
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveButton) {
            updateTextController.execute(
                    titleDescEditBoxes.getTitleText(),
                    titleDescEditBoxes.getDescriptionText()
            );
        }

        else if (e.getSource() == cancelButton) {
            updateTextController.cancelUpdate();
        }
    }

    public void setUpdateTextController(UpdateTextController updateTextController) {
        this.updateTextController = updateTextController;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getNewValue() instanceof UpdateTextState newState) {
            if (newState.getUpdateTextError() != null) {
                JOptionPane.showMessageDialog(this, newState.getUpdateTextError(), "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                this.titleDescEditBoxes.setTitleText(newState.getTitle());
                this.titleDescEditBoxes.setDescriptionText(newState.getDescription());
            }

        }
    }
}

