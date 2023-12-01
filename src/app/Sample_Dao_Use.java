package app;

import Entities.Card;
import Entities.User;
import data_access.FileCardDataAccessObject;
import use_case.StatsGallery.StatsGallery;
import use_case.gallery.Gallery;
import use_case.generatecard.GenerateCardDataAccessInterFace;
import use_case.generatecard.GenerateImageDataAccessInterface;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Sample_Dao_Use {
    public static void main(String[] args) throws IOException {


        FileCardDataAccessObject cardDAO = new FileCardDataAccessObject("src/DB/cards.txt", "src/db/cards.db");

        User user = cardDAO.getUser("TestUser");

        Gallery gallery = new Gallery(cardDAO, user);

        HashMap<Card, Boolean> booleanHashMap = (gallery.execute());

        StatsGallery statsGallery = new StatsGallery(cardDAO);



        for (Map.Entry<Card, Boolean> entry : booleanHashMap.entrySet()) {
            Card card = entry.getKey();
            // Retrieve and print the card name and ID
            System.out.println("Card Name: " + card.getName() + ", Card ID: " + card.getId() + ", User Ownes it" + entry.getValue());

            System.out.println(statsGallery.execute(card.getId()).getName());
        }



    }
}