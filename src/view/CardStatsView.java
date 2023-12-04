package view;

import Entities.Card;
import Entities.User;
import data_access.FileCardDataAccessObject;
import interface_adapter.card_stats.CardStatsState;
import interface_adapter.card_stats.CardStatsViewModel;
import use_case.StatsGallery.StatsGallery;
import use_case.gallery.Gallery;

import javax.swing.*;
import javax.swing.text.View;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.HashMap;

public class CardStatsView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "card_stats";

    public CardStatsView(CardStatsViewModel viewModel) {
        JLabel title = new JLabel("card_stats");
        title.setAlignmentX(CENTER_ALIGNMENT);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        CardStatsState state = viewModel.getState();
        JLabel cardImage = new JLabel(new ImageIcon(state.getImgpath()));
        cardImage.setAlignmentX(CENTER_ALIGNMENT);
        JLabel cardName = new JLabel("name: " + state.getName());
        JLabel rarity = new JLabel("rarity: " + state.getRarity());
        JLabel cardDescription = new JLabel("desc: " + state.getDescription());
        this.add(cardImage);
        this.add(cardName);
        this.add(rarity);
        this.add(cardDescription);
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {

    }

    @Override
    public void propertyChange(java.beans.PropertyChangeEvent evt) {

    }
}
