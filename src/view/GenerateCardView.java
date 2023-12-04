package view;

import interface_adapter.generatecard.GenerateCardController;
import interface_adapter.generatecard.GenerateCardState;
import interface_adapter.generatecard.GenerateCardViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class GenerateCardView extends JPanel implements ActionListener, PropertyChangeListener {
    private final JTextField themeInputField;
    private final JButton generateButton;
    private final JLabel cardImageLabel;
    private final JLabel cardDisplayLabel;
    private final JLabel errorLabel;
    private final GenerateCardController generateCardController;
    private final GenerateCardViewModel generateCardViewModel;

    public GenerateCardView(GenerateCardController generateCardController, GenerateCardViewModel generateCardViewModel) {
        this.generateCardController = generateCardController;
        this.generateCardViewModel = generateCardViewModel;

        // Initialize UI components
        themeInputField = new JTextField(20);
        generateButton = new JButton("Generate Card");
        cardImageLabel = new JLabel();
        cardDisplayLabel = new JLabel();
        errorLabel = new JLabel();

        // Layout
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(cardImageLabel);
        add(themeInputField);
        add(generateButton);

        add(cardDisplayLabel);
        add(errorLabel);

        // Action Listener
        generateButton.addActionListener(this);
        generateCardViewModel.addPropertyChangeListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == generateButton) {
            String theme = themeInputField.getText();
            generateCardController.onClickGenerate(theme);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName())) {
            updateView((GenerateCardState) evt.getNewValue());
        }
    }

    private void updateView(GenerateCardState state) {
        if (state.getGeneratedCard() != null) {
            cardDisplayLabel.setText("Generated Card: " + state.getGeneratedCard().getName());
            errorLabel.setText("");
            updateCardImage(state.getGeneratedCard().getimgpath());
        } else if (state.getError() != null) {
            errorLabel.setText("Error: " + state.getError());
            cardDisplayLabel.setText("");
            cardImageLabel.setIcon(null); // Clear the image if there is an error
        }
    }

    private void updateCardImage(String imgPath) {
        try {
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(imgPath).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
            cardImageLabel.setIcon(imageIcon);
        } catch (Exception e) {
            cardImageLabel.setIcon(null);
            errorLabel.setText("Error displaying image");
        }
    }
}
