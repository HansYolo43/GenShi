package view;

import interface_adapter.card_stats.CardStatsController;
import interface_adapter.card_stats.CardStatsState;
import interface_adapter.card_stats.CardStatsViewModel;

import javax.swing.*;

import java.awt.*;

import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

public class CardStatsView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "card_stats";
    private final CardStatsViewModel viewModel;
    private final CardStatsController controller;
    private JLabel cardImageLabel;


    private JLabel cardNameLabel;
    private JLabel rarityLabel;
    private JLabel descriptionLabel;

    private JButton backButton;

    public CardStatsView(CardStatsViewModel viewModel, CardStatsController controller) {
        this.viewModel = viewModel;
        this.controller = controller;
        this.viewModel.addPropertyChangeListener(this);


        setLayout(new BorderLayout());
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Card Name at the top
        cardNameLabel = new JLabel();
        cardNameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        headerPanel.add(cardNameLabel, BorderLayout.NORTH);

        // Card Image
        cardImageLabel = new JLabel();
        cardImageLabel.setHorizontalAlignment(JLabel.CENTER);
        headerPanel.add(cardImageLabel, BorderLayout.CENTER);

        // Rarity and Description
        rarityLabel = new JLabel();
        descriptionLabel = new JLabel();
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.add(rarityLabel);
        detailsPanel.add(descriptionLabel);

        headerPanel.add(detailsPanel, BorderLayout.SOUTH);

        add(headerPanel, BorderLayout.CENTER);
        backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            controller.executeBack();
        });

        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        footerPanel.add(backButton);
        add(footerPanel, BorderLayout.SOUTH);

        updateViewFromState(viewModel.getState());
    }

    private void updateViewFromState(CardStatsState state) {
        // Update the view components based on state
        cardNameLabel.setText(state.getName());
        rarityLabel.setText("Rarity: " + state.getRarity());
        descriptionLabel.setText("Description: " + state.getDescription());

        String imagePath = state.getImgpath();
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(512, 512, Image.SCALE_SMOOTH));
        cardImageLabel.setIcon(imageIcon);
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        System.out.println("Click " + e.getActionCommand());
    }

    @Override
    public void propertyChange(java.beans.PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName())) {

            updateViewFromState((CardStatsState) evt.getNewValue());
        }

    }
}
