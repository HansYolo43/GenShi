package use_case.lootbox;

import Entities.Card;

import java.util.Random;

public interface LootboxUserDataAccessInterface {

    Card getCard(int cardId);

    public abstract Integer randomcard();

    void updateusercard(Integer cardID);
}
