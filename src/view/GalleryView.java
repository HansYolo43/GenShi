package view;

import Entities.Card;
import Entities.User;
import data_access.FileCardDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.gallery.GalleryState;
import interface_adapter.gallery.GalleryViewModel;
import use_case.gallery.Gallery;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    // TODO: add controllers

    public GalleryView(GalleryViewModel viewModel) throws IOException {
        this.viewModel = viewModel;
        JLabel title = new JLabel("gallery");
        title.setAlignmentX(CENTER_ALIGNMENT);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        JPanel buttons = new JPanel();
        // TODO: expand this so that the number of cardViewButtons is equal to the number of cards in the gallery
        this.backButton = new JButton(GalleryViewModel.BACK_BUTTON_LABEL);
        buttons.add(backButton);
//        this.cardViewButton = new JButton();
//        String path = "/Users/dmitriivlasov/IdeaProjects/GenShi/src/view/img.png";
//        Image image = new ImageIcon(path).getImage();
//        image = image.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
//
//        this.cardViewButton.setIcon(new ImageIcon(image));
//        buttons.add(cardViewButton);
        FileCardDataAccessObject cardDAO = new FileCardDataAccessObject("src/DB/cards.txt", "src/db/cards.db");
        User user = cardDAO.getUser("TestUser");
        cardDAO.setActiveUser(user);

        Gallery gallery = new Gallery(cardDAO);
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

            Image image = new ImageIcon(imgPath).getImage();
            image = image.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
            cardViewButton.setIcon(new ImageIcon(image));
            buttons.add(cardViewButton);

            cardViewButton.addActionListener(
                    new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            if (evt.getSource().equals(cardViewButton)) {
                                GalleryState currentState = viewModel.getState(); // not really needed here
                                // use your controller(s)
                                System.out.println("Card view button clicked");
                                // TODO: for testing, remove later
                            }
                        }
                    }
            );
            // Retrieve and print the card name and ID

            //System.out.println("Card Name: " + card.getName() + ", Card ID: " + card.getId() + ", User Ownes it" + entry.getValue());
        }


        this.backButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(backButton)) {
                            GalleryState currentState = viewModel.getState(); // not really needed here
                            // use your controller(s)
                        }
                    }
                }
        );


        this.add(buttons);
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
            JOptionPane.showMessageDialog(this, state.getBackError());
        }
        if (state.getCardViewError() != null) {
            JOptionPane.showMessageDialog(this, state.getCardViewError());
        }
    }
}
