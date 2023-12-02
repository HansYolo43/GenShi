package use_case.gallery;

import Entities.Card;

import java.util.ArrayList;

public interface GalleryUserDataAccessInterface {
    ArrayList<Integer> CardManager();

    Card getCard(int cardId);
}
