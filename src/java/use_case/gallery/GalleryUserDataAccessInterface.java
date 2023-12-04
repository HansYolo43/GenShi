package use_case.gallery;

import Entities.Card;
import Entities.User;

import java.util.ArrayList;

public interface GalleryUserDataAccessInterface {
    ArrayList<Integer> CardManager();

    Card getCard(int cardId);

    User getActiveUser();
}
