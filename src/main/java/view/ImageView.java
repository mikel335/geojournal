package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class ImageView extends JPanel implements ActionListener {

    private static final int IMAGE_WIDTH = 350;
    private static final int IMAGE_HEIGHT = 300;

    public final JPanel imagesPanel;
    public final JButton uploadButton;
    public final JButton deleteAllButton;
    public final ArrayList<JPanel> imagePanelsList;

    public ImageView() {
        setLayout(new BorderLayout());

        imagesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        imagePanelsList = new ArrayList<>();

        JScrollPane scrollPane = new JScrollPane(imagesPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane, BorderLayout.CENTER);

        uploadButton = new JButton("Upload Image");
        uploadButton.setForeground(Color.GREEN.darker());
        uploadButton.addActionListener(this);
        buttonPanel.add(uploadButton);

        deleteAllButton = new JButton("Delete All Images");
        deleteAllButton.setForeground(Color.RED);
        deleteAllButton.addActionListener(this);
        buttonPanel.add(deleteAllButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == uploadButton) {
            uploadImage();
        } else if (e.getSource() == deleteAllButton) {
            // Show confirmation dialog before deleting all images
            int response = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to do this?\nThey will be lost forever",
                    "Delete All Images",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);

            if (response == JOptionPane.YES_OPTION) {
                deleteAllImages();
            }
        }
    }

    public void uploadImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
                "Image files", "jpg", "png", "jpeg", "gif"));

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            if (imagePanelsList.size() > 11) {
                JOptionPane.showMessageDialog(this,
                        "You have exceeded the maximum number of images allowed!",
                        "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                File selectedFile = fileChooser.getSelectedFile();
                ImageIcon imageIcon = new ImageIcon(selectedFile.getAbsolutePath());

                Image image = imageIcon.getImage();
                Image resizedImage = image.getScaledInstance(IMAGE_WIDTH, IMAGE_HEIGHT, Image.SCALE_SMOOTH);
                ImageIcon resizedIcon = new ImageIcon(resizedImage);
                JLabel imageLabel = new JLabel(resizedIcon);
                imageLabel.setHorizontalAlignment(SwingConstants.CENTER);

                JButton deleteSingleImageButton = new JButton("Delete");
                deleteSingleImageButton.addActionListener(event -> deleteSingleImage(imageLabel));
                JPanel imagePanel = new JPanel(new BorderLayout());
                imagePanel.add(imageLabel, BorderLayout.CENTER);
                imagePanel.add(deleteSingleImageButton, BorderLayout.SOUTH);

                imagePanelsList.add(imagePanel);
                imagesPanel.add(imagePanel);
                imagesPanel.setPreferredSize(new Dimension(210 * imagePanelsList.size(), IMAGE_HEIGHT + 40));
                imagesPanel.revalidate();
                imagesPanel.repaint();
            }
        }
    }

    public void deleteSingleImage(JLabel imageLabel) {
        imagesPanel.remove(imageLabel.getParent());
        imagePanelsList.remove(imageLabel.getParent());
        imagesPanel.revalidate();
        imagesPanel.repaint();
    }

    private void deleteAllImages() {
        imagesPanel.removeAll();
        imagePanelsList.clear();
        imagesPanel.revalidate();
        imagesPanel.repaint();
    }
}
