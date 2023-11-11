package app;

import Entities.Card;
import data_access.FileCardDataAccessObject;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            FileCardDataAccessObject cardDAO = new FileCardDataAccessObject("java/src/cards.txt");

            // Adding a new card
            Card newCard = new Card(3, "New Card", 103, "New card description");
            Card card1 = new Card(1, "Quik-Slik Khaos", 101, "Description of Quik-Slik Khaos");
            Card card2 = new Card(2, "Xeno Bladebreaker", 102, "Description of Xeno Bladebreaker");

            cardDAO.addCard(newCard);
            cardDAO.addCard(card1);
            cardDAO.addCard(card2);

            // Updating a card

            // Setting a new description
//            cardDAO.setDescription(1, "Updated Description");

            // Removing a card
//            cardDAO.removeCard(2);

            cardDAO.save();





        } catch (
                IOException e) {
            e.printStackTrace();
            ;
        }
    }
}