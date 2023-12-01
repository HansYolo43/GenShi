package use_case.gallery;

import Entities.Card;
import Entities.User;
import data_access.FileCardDataAccessObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Gallery {
    private User user; //replace with common user

    private FileCardDataAccessObject fileCardDataAccessObject;

    public Gallery(FileCardDataAccessObject Dao, User user){
        this.fileCardDataAccessObject = Dao;
        this.user = user;

    }

    public HashMap<Card, Boolean> execute() {
        return findcommoncards();
    }

    private HashMap<Card, Boolean> findcommoncards(){

        HashMap<Entities.Card, Boolean> cardBooleanHashMap = new HashMap<Card, Boolean>();

        ArrayList<Integer> card_array1  =  fileCardDataAccessObject.CardManager();

        ArrayList<Integer> card_array2 =  user.getCards_owned();

        for( Integer i:card_array1 ){
            if(card_array2.contains(i)){


                cardBooleanHashMap.put(fileCardDataAccessObject.getCard(i), Boolean.TRUE);

            }else {

                cardBooleanHashMap.put(fileCardDataAccessObject.getCard(i), Boolean.FALSE);
            }
        }

        return cardBooleanHashMap;

    }

    public void sortbyrarity(HashMap<Card, Boolean> cardBooleanHashMap){
        //sort by rarity if needed
        }

}
