package app;


import Entities.Card;
import data_access.FileCardDataAccessObject;
import use_case.generatecard.GenerateCardDataAccessInterFace;
import use_case.generatecard.GenerateImageDataAccessInterface;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        String Apikey = "API-KEY";
        String theme = "Create a simple character card of a pig wearing a pink dress ";
        FileCardDataAccessObject cardDAO = new FileCardDataAccessObject("src/DB/cards.txt", "src/db/test.db");


        GenerateCardDataAccessInterFace cardex = new GenerateCardDataAccessInterFace(Apikey, theme, cardDAO);


        Integer cardID = cardex.generateAndSaveCharacters();

        Card card = cardDAO.getCard(cardID);

        System.out.println(card);

        GenerateImageDataAccessInterface cardop = new GenerateImageDataAccessInterface(Apikey, cardDAO);

        String character = "pig wearing a pink dress";

        String sampleprompt = String.format("Create an action figure card for [Character Name]. The card should feature a detailed illustration of [Character Name]. The background of the card should have thematic elements related to the character's backstory or abilities, like [specific themes or motifs]. Ensure the character's pose is dynamic and fitting for an action figure, and the card design is vivid and eye-catching.", character);
        String maybe = "The background of the card should have thematic elements related to the character's backstory or abilities, like [specific themes or motifs]. Ensure the character's pose is dynamic and fitting for an action figure, and the card design is vivid and eye-catching.";

        cardop.generateImageForCard(card.getId(), sampleprompt +maybe );

        cardDAO.exit();

//
//        createNewDatabase("test.db");



//        String dbpath = "src/db/test.db";
//
//        DatabaseHelper databaseHelper = new DatabaseHelper(dbpath);
//
//
////        databaseHelper.createTables();
//
//        FileCardDataAccessObject Dao = new FileCardDataAccessObject("src/db/cards.txt","src/db/test.db" );
//
//        CardManager cad = new CardManager( Dao.CardManager());
//
//        ArrayList<Integer> pop  = Dao.CardManager();
//
//
//
//        for (Integer i : pop) {
//            System.out.println(i);
//            Card card = Dao.getCard(i);
//
//            databaseHelper.insertCardIntoSQLite(card);
//
//        }
//
//
    }



        /**
         * @param args the command line arguments
         */

}