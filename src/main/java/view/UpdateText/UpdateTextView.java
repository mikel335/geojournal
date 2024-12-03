package view.UpdateText;

import interface_adapter.updateText.UpdateTextController;
import interface_adapter.updateText.UpdateTextState;
import interface_adapter.updateText.UpdateTextViewModel;
import view.Components.Colors;
import view.Components.StyledButton;

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
    final JButton saveButton = new StyledButton("Save");
    final JButton cancelButton = new StyledButton("Cancel");

    public UpdateTextView(UpdateTextViewModel updateTextModel) {
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        setBackground(Colors.lightBlue);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.viewModel = updateTextModel;
        this.viewModel.addPropertyChangeListener(this);

        titleDescEditBoxes = new TitleDescEditBoxes(
                viewModel.getState().getTitle(),
                viewModel.getState().getDescription()
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

        add(titleDescEditBoxes); // Text area view at the top
        add(buttonPanel1); // Button panel at the bottom (aligned right)
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

