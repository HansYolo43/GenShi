package use_case.lootbox;

import data_access.FileCardDataAccessObject;

import java.util.Random;

public class Lootbox {

    private LootboxUserDataAccessInterface fileCardDataAccessObject;

    public Lootbox(LootboxUserDataAccessInterface fileCardDataAccessObject){
        this.fileCardDataAccessObject = fileCardDataAccessObject;

    }

    public Entities.Card execute(){
        Integer ID = fileCardDataAccessObject.randomcard();
        fileCardDataAccessObject.updateusercard(ID);
        return fileCardDataAccessObject.getCard(ID);

    }

}
