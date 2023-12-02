package view;

import interface_adapter.gallery.GalleryState;
import interface_adapter.gallery.GalleryViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class GalleryView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "gallery";
    private final GalleryViewModel viewModel;

    // we have to keep track of two possible errors: cardView and back
    private final JButton backButton;
    private final JButton cardViewButton;
    // TODO: add controllers

    public GalleryView(GalleryViewModel viewModel) {
        this.viewModel = viewModel;
        JLabel title = new JLabel("gallery");
        title.setAlignmentX(CENTER_ALIGNMENT);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        JPanel buttons = new JPanel();
        // TODO: expand this so that the number of cardViewButtons is equal to the number of cards in the gallery
        this.backButton = new JButton(GalleryViewModel.BACK_BUTTON_LABEL);
        this.cardViewButton = new JButton();
        String path = "/Users/dmitriivlasov/IdeaProjects/GenShi/src/view/img.png";
        Image image = new ImageIcon(path).getImage();
        image = image.getScaledInstance(100, 100, Image.SCALE_DEFAULT);

        this.cardViewButton.setIcon(new ImageIcon(image));
        buttons.add(backButton);
        buttons.add(cardViewButton);

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

        this.cardViewButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(cardViewButton)) {
                            GalleryState currentState = viewModel.getState(); // not really needed here
                            // use your controller(s)
                            System.out.println("Card view button clicked");
                        }
                    }
                }
        );
        this.add(buttons);
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
