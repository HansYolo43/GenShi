package view;

import Entities.Card;
import Entities.User;
import data_access.FileCardDataAccessObject;
import interface_adapter.card_stats.CardStatsState;
import interface_adapter.card_stats.CardStatsViewModel;
import interface_adapter.main_menu.MainMenuState;
import use_case.gallery.Gallery;

import javax.swing.*;
import javax.swing.text.View;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.HashMap;

public class CardStatsView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "card_stats";
    private final CardStatsViewModel viewModel;

    private String cardImgPath;
    private String cardName;
    private String rarity;
    private String description;
    private JLabel cardImage;
    private JLabel cardNameLabel;
    private JLabel rarityLabel;
    private JLabel descriptionLabel;

    public CardStatsView(CardStatsViewModel viewModel) {
        this.viewModel = viewModel;
        this.viewModel.addPropertyChangeListener(this);
        JLabel title = new JLabel("card_stats");
        title.setAlignmentX(CENTER_ALIGNMENT);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        CardStatsState state = viewModel.getState();
        this.cardImage = new JLabel(new ImageIcon(state.getImgpath()));
        this.cardImage.setAlignmentX(CENTER_ALIGNMENT);
        this.cardName = state.getName();
        this.rarity = state.getRarity();
        this.description = state.getDescription();
        this.cardNameLabel = new JLabel("name: " + this.cardName);
        this.rarityLabel = new JLabel("rarity: " + this.rarity);
        this.descriptionLabel = new JLabel("desc: " + this.description);
        this.add(cardImage);
        this.add(cardNameLabel);
        this.add(rarityLabel);
        this.add(descriptionLabel);
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        System.out.println("Click " + e.getActionCommand());
    }

    @Override
    public void propertyChange(java.beans.PropertyChangeEvent evt) {
        CardStatsState state = (CardStatsState) evt.getNewValue();
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
