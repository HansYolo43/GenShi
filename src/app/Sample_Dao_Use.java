package app;

import Entities.Card;
import data_access.FileCardDataAccessObject;
import use_case.generatecard.GenerateCardDataAccessInterFace;
import use_case.generatecard.GenerateImageDataAccessInterface;

import java.io.IOException;

public class Sample_Dao_Use {
    public static void main(String[] args) throws IOException {

        String Apikey = "$OPENAI_API_KEY";
        String theme = " loaf of bread wearing white glove";
        FileCardDataAccessObject cardDAO = new FileCardDataAccessObject("src/DB/cards.txt");


        GenerateCardDataAccessInterFace cardex = new GenerateCardDataAccessInterFace(Apikey , theme , cardDAO);


        Integer cardID = cardex.generateAndSaveCharacters();

        Card card = cardDAO.getCard(cardID);

        System.out.println(card);

        GenerateImageDataAccessInterface cardop = new GenerateImageDataAccessInterface(Apikey,cardDAO);

        cardop.generateImageForCard(card.getId() , "Create a simple character card of a loaf of bread wearing white glove");




    }
}