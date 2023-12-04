package view;

import interface_adapter.gallery.GalleryState;
import interface_adapter.gallery.GalleryViewModel;
import interface_adapter.gambling.GamblingController;
import interface_adapter.gambling.GamblingState;
import interface_adapter.gambling.GamblingViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class GamblingView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "gambling";
    private final GamblingViewModel viewModel;
    private final GamblingController controller;
    private String cardImgPath;
    private String cardName;
    private String rarity;
    private String description;
    private JLabel cardImage;
    private JLabel cardNameLabel;
    private JLabel rarityLabel;
    private JLabel descriptionLabel;
    private final JButton gambleButton;
    private final JButton backButton;

    public GamblingView(GamblingViewModel viewModel, GamblingController controller) {
        this.viewModel = viewModel;
        this.viewModel.addPropertyChangeListener(this);
        this.controller = controller;
        JLabel title = new JLabel("gambling");
        title.setAlignmentX(CENTER_ALIGNMENT);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.cardImage = new JLabel();
        this.cardNameLabel = new JLabel("name: ");
        this.rarityLabel = new JLabel("rarity: ");
        this.descriptionLabel = new JLabel("desc: ");
        this.add(cardImage);
        this.add(cardNameLabel);
        this.add(rarityLabel);
        this.add(descriptionLabel);
        JPanel buttons = new JPanel();
        this.gambleButton = new JButton(GamblingViewModel.GAMBLING_BUTTON_LABEL);
        buttons.add(gambleButton);
        this.gambleButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(gambleButton)) {
                            GamblingState currentState = viewModel.getState(); // not really needed here
                            // use your controller(s)
                            controller.execute();
                        }
                    }
                }
        );
        this.backButton = new JButton(GalleryViewModel.BACK_BUTTON_LABEL);
        buttons.add(backButton);
        this.backButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(backButton)) {
                            GamblingState currentState = viewModel.getState(); // not really needed here
                            // use your controller(s)
                            controller.executeBack();
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
        GamblingState state = (GamblingState) evt.getNewValue();
        this.cardName = state.getName();
        this.rarity = state.getRarity();
        this.description = state.getDescription();
        this.cardImgPath = state.getImgpath();
        cardImage.setIcon(new ImageIcon(this.cardImgPath));
        cardNameLabel.setText("name: " + this.cardName);
        rarityLabel.setText("rarity: " + this.rarity);
        descriptionLabel.setText("desc: " + this.description);
        this.add(cardImage);
        this.add(cardNameLabel);
        this.add(rarityLabel);
        this.add(descriptionLabel);
    }
}
