package app;

import Entities.Card;
import Entities.Stats;
import data_access.FileCardDataAccessObject;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            FileCardDataAccessObject cardDAO = new FileCardDataAccessObject("java/src/cards.txt");

            cardDAO.load();

            // Adding a new card
            for( int i = 0; i <= 10; i++) {
                Stats stats = new Stats(23, "idk", 34, 23, 43, 43);
                Card newCard = new Card(i, "New Card", 109, "Desc", "path", stats);


                cardDAO.addCard(newCard);
//            cardDAO.addCard(card1);
//            cardDAO.addCard(card2);
            }
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