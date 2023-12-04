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

    private String cardName;
    private String rarity;
    private String description;

    public CardStatsView(CardStatsViewModel viewModel) {
        this.viewModel = viewModel;
        this.viewModel.addPropertyChangeListener(this);
        JLabel title = new JLabel("card_stats");
        title.setAlignmentX(CENTER_ALIGNMENT);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        CardStatsState state = viewModel.getState();
        JLabel cardImage = new JLabel(new ImageIcon(state.getImgpath()));
        cardImage.setAlignmentX(CENTER_ALIGNMENT);
        viewModel.firePropertyChanged();
        this.cardName = state.getName();
        this.rarity = state.getRarity();
        this.description = state.getDescription();
        JLabel cardName = new JLabel("name: " + this.cardName);
        JLabel rarity = new JLabel("rarity: " + this.rarity);
        JLabel cardDescription = new JLabel("desc: " + this.description);
        this.add(cardImage);
        this.add(cardName);
        this.add(rarity);
        this.add(cardDescription);
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
    }
}
