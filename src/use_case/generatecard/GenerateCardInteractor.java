package use_case.generatecard;

import Entities.Card;
import api.GenerateCardDataAccessInterFace;
import api.GenerateImageDataAccessInterface;
import data_access.FileCardDataAccessObject;

import java.io.IOException;

public class GenerateCardInteractor implements GenerateCardInputBoundary{

    private FileCardDataAccessObject fileCardDataAccessObject;

    private final String Api_Key;

    private GenerateCardOutputBoundary presenter;

    public GenerateCardInteractor(FileCardDataAccessObject fileCardDataAccessObject, String Api_key, GenerateCardOutputBoundary presenter){
        this.Api_Key = Api_key;
        this.fileCardDataAccessObject = fileCardDataAccessObject;
        this.presenter = presenter;
    }

    @Override
    public void execute(String theme) {
        GenerateCardDataAccessInterFace CardGen = new GenerateCardDataAccessInterFace(Api_Key, theme, fileCardDataAccessObject);

        GenerateImageDataAccessInterface CardImage =  new GenerateImageDataAccessInterface(Api_Key ,fileCardDataAccessObject);

        Integer CardID = null;
        try {
            CardID = CardGen.generateAndSaveCharacters();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            CardImage.generateImageForCard(CardID, theme);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Card generatedCard = fileCardDataAccessObject.getCard(CardID);

        presenter.presentGeneratedCard(generatedCard);
    }

    public void executeBack(){
        presenter.prepareBackView();
    }
}
