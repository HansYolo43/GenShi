package use_case.generatecard;

import api.GenerateCardDataAccessInterFace;
import api.GenerateImageDataAccessInterface;
import data_access.FileCardDataAccessObject;

import java.io.IOException;

public class GenerateCardInteractor {

    private FileCardDataAccessObject fileCardDataAccessObject;

    private final String Api_Key;

    private final String theme;

    public GenerateCardInteractor(FileCardDataAccessObject fileCardDataAccessObject, String Api_key, String theme){
        this.Api_Key = Api_key;
        this.theme = theme;
        this.fileCardDataAccessObject = fileCardDataAccessObject;
    }

    public Integer execute() throws IOException {
        GenerateCardDataAccessInterFace CardGen = new GenerateCardDataAccessInterFace(Api_Key, theme, fileCardDataAccessObject);

        GenerateImageDataAccessInterface CardImage =  new GenerateImageDataAccessInterface(Api_Key ,fileCardDataAccessObject);

        Integer CardID = CardGen.generateAndSaveCharacters();

        CardImage.generateImageForCard(CardID, theme);

        return CardID;



    }
}
