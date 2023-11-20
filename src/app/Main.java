package app;

import Entities.Card;
import Entities.Stats;
import data_access.FileCardDataAccessObject;
import use_case.generatecard.GenerateCardDataAccessInterFace;
import use_case.generatecard.GenerateImageDataAccessInterface;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {

        String Apikey = "$OPENAI_API_KEY";
        String theme = "Greek Era";
        FileCardDataAccessObject cardDAO = new FileCardDataAccessObject("src/DB/cards.txt");


        GenerateCardDataAccessInterFace cardex = new GenerateCardDataAccessInterFace(Apikey , theme , cardDAO);


        Integer cardID = cardex.generateAndSaveCharacters();

        Card card = cardDAO.getCard(cardID);

        System.out.println(card);

        GenerateImageDataAccessInterface cardop = new GenerateImageDataAccessInterface(Apikey,cardDAO,"Generate an action figure card of" + card.getName());

        cardop.generateImageForCard(card.getId() , card.getDesc());




    }
}