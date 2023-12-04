package use_case.StatsGallery;

import Entities.Card;

public interface StatsGalleryDataAccessInterface {
    Card getCard(int cardId);
}
