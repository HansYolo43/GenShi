package app;

import data_access.FileCardDataAccessObject;
import interface_adapter.generatecard.GenerateCardController;
import interface_adapter.generatecard.GenerateCardPresenter;
import interface_adapter.generatecard.GenerateCardState;
import interface_adapter.generatecard.GenerateCardViewModel;
import use_case.generatecard.GenerateCardInputBoundary;
import use_case.generatecard.GenerateCardInteractor;
import view.GenerateCardView;

import javax.swing.*;
import java.io.IOException;

public class Sample_Dao_Use {
    public static void main(String[] args) {
        // Set up the Swing frame
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Card Generation");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 800);

            // Use the factory to create the GenerateCardView
            FileCardDataAccessObject fileCardDao = null;
            try {
                fileCardDao =new FileCardDataAccessObject("src/DB/cards.txt", "src/db/cards.db");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            GenerateCardView generateCardView = GenerateCardUseCaseFactory.createGenerateCardView(fileCardDao);

            // Add the GenerateCardView to the frame
            frame.add(generateCardView);

            // Display the frame
            frame.pack();
            frame.setLocationRelativeTo(null); // Center the frame
            frame.setVisible(true);
        });
    }
}