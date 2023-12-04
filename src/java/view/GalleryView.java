package view;

import Entities.Card;
import Entities.User;
import data_access.FileCardDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.gallery.GalleryController;
import interface_adapter.gallery.GalleryState;
import interface_adapter.gallery.GalleryViewModel;
import use_case.StatsGallery.StatsGalleryDataAccessInterface;
import use_case.gallery.Gallery;
import use_case.gallery.GalleryUserDataAccessInterface;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GalleryView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "gallery";
    private final GalleryViewModel viewModel;

    // we have to keep track of two possible errors: cardView and back
    private final JButton backButton;
    private GalleryController galleryController;
    private GalleryUserDataAccessInterface dao;

    private JPanel buttons;

    private final JButton refreshButton;

    public GalleryView(GalleryViewModel viewModel, GalleryController galleryController, GalleryUserDataAccessInterface dao) throws IOException {
        this.viewModel = viewModel;
        this.galleryController = galleryController;
        this.dao = dao;
        this.viewModel.addPropertyChangeListener(this);
        JLabel title = new JLabel("gallery");
        this.setLayout(new BorderLayout());
        title.setAlignmentX(CENTER_ALIGNMENT);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.buttons = new JPanel();


        this.backButton = new JButton(GalleryViewModel.BACK_BUTTON_LABEL);
        buttons.add(backButton);

        this.refreshButton = new JButton("Refresh Gallery");
        buttons.add(refreshButton);

        refreshButton.addActionListener(evt -> {
            try {
                updateGalleryView(dao);
            } catch (IOException e) {
                e.printStackTrace(); // handle exception appropriately
            }
        });



        Gallery gallery = new Gallery(dao);
        HashMap<Card, Boolean> booleanHashMap = (gallery.execute());

        for (Map.Entry<Card, Boolean> entry : booleanHashMap.entrySet()) {
            Card card = entry.getKey();
            JButton cardViewButton = new JButton();
            String imgPath = card.getimgpath();
            if (!canLoadImage(imgPath)) {
                imgPath = imgPath.replace("\\", "/");
                // Set a default icon or handle the error
                if (!canLoadImage(imgPath)) {
                    System.out.println("Image there: " + imgPath);
                }
            }

            Image image = ImageIO.read(new File(imgPath));
            // convert to grayscale if the player does not have it
            if (!entry.getValue()) {
                ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
                ColorConvertOp op = new ColorConvertOp(cs, null);
                BufferedImage bi = op.filter((BufferedImage) image, null);
                image = bi.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
            }
            else {
                image = image.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
            }
            cardViewButton.setIcon(new ImageIcon(image));
            cardViewButton.addActionListener(
                    new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            if (evt.getSource().equals(cardViewButton)) {
                                GalleryState currentState = viewModel.getState(); // not really needed here
                                // use your controller(s)
                                System.out.println("Card view button clicked");
                                System.out.println(card.getName());
                                viewModel.firePropertyChanged();
                                galleryController.execute(card);
                                viewModel.firePropertyChanged();
                            }
                        }
                    }
            );
            buttons.add(cardViewButton);
        }


        this.backButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(backButton)) {
                            GalleryState currentState = viewModel.getState(); // not really needed here
                            // use your controller(s)
                            galleryController.executeBack();
                        }
                    }
                }
        );
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setHorizontalAlignment(JLabel.CENTER);
        this.add(title, BorderLayout.NORTH);

        buttons.setLayout(new GridLayout(0, 4, 10, 10)); // Grid layout with margins
        buttons.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding



        this.add(buttons);
        JScrollPane scrollPane = new JScrollPane(buttons);
        this.add(scrollPane, BorderLayout.CENTER);
    }

    private boolean canLoadImage(String imagePath) {
        try {
            ImageIcon icon = new ImageIcon(imagePath);
            return icon.getImageLoadStatus() == MediaTracker.COMPLETE;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // this is a debug message for when the user clicks on one of the buttons
        System.out.println("Click " + e.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // this is for error messages
        GalleryState state = (GalleryState) evt.getNewValue();
        if (state.getBackError() != null) {
            try {
                updateGalleryView(dao);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            JOptionPane.showMessageDialog(this, state.getBackError());
        }
        if (state.getCardViewError() != null) {
            JOptionPane.showMessageDialog(this, state.getCardViewError());
        }
    }

    public void updateGalleryView(GalleryUserDataAccessInterface dao) throws IOException {
        // Clear existing buttons
        buttons.removeAll();

        // Add back button and any other static buttons
        buttons.add(backButton);
        buttons.add(refreshButton);

        // Fetch the latest data from DAO

        Gallery gallery = new Gallery(dao);
        HashMap<Card, Boolean> booleanHashMap = (gallery.execute());

        for (Map.Entry<Card, Boolean> entry : booleanHashMap.entrySet()) {
            Card card = entry.getKey();
            JButton cardViewButton = new JButton();
            String imgPath = card.getimgpath();
            if (!canLoadImage(imgPath)) {
                imgPath = imgPath.replace("\\", "/");
                // Set a default icon or handle the error
                if (!canLoadImage(imgPath)) {
                    System.out.println("Image there: " + imgPath);
                }
            }

            Image image = ImageIO.read(new File(imgPath));
            // convert to grayscale if the player does not have it
            if (!entry.getValue()) {
                ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
                ColorConvertOp op = new ColorConvertOp(cs, null);
                BufferedImage bi = op.filter((BufferedImage) image, null);
                image = bi.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
            }
            else {
                image = image.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
            }
            cardViewButton.setIcon(new ImageIcon(image));
            cardViewButton.addActionListener(
                    new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            if (evt.getSource().equals(cardViewButton)) {
                                GalleryState currentState = viewModel.getState(); // not really needed here
                                // use your controller(s)
                                System.out.println("Card view button clicked");
                                System.out.println(card.getName());
                                viewModel.firePropertyChanged();
                                galleryController.execute(card);
                                viewModel.firePropertyChanged();
                            }
                        }
                    }
            );
            buttons.add(cardViewButton);
        }





        buttons.setLayout(new GridLayout(0, 4, 10, 10));
        buttons.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Ensure the updated buttons panel is displayed correctly
        buttons.revalidate();
        buttons.repaint();

    }
}
