package viewWithCA.UpdateText;

import interface_adapter.updateText.UpdateTextController;
import interface_adapter.updateText.UpdateTextViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateTextView extends JPanel implements ActionListener {
    private final UpdateTextViewModel viewModel;
    private UpdateTextController updateTextController;

    final JButton saveButton = new JButton("Save");
    final JButton cancelButton = new JButton("Cancel");

    TitleDescEditBoxes textEditPanel;

    public UpdateTextView(UpdateTextViewModel updateTextModel) {
        this.viewModel = updateTextModel;

        textEditPanel = new TitleDescEditBoxes(
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
        add(textEditPanel, BorderLayout.NORTH); // Text area view at the top
        add(buttonPanel1, BorderLayout.CENTER); // Button panel at the bottom (aligned right)
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveButton) {
            updateTextController.execute(
                    textEditPanel.getTitleText(),
                    textEditPanel.getDescriptionText()
            );
        }

        else if (e.getSource() == cancelButton) {
            updateTextController.execute(
                    viewModel.getState().getTitle(),
                    viewModel.getState().getDescription()
            );
        }
    }

    public void setUpdateTextController(UpdateTextController updateTextController) {
        this.updateTextController = updateTextController;
    }
}

