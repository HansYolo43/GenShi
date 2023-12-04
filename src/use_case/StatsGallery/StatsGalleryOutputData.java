package use_case.StatsGallery;

import Entities.Card;

public class StatsGalleryOutputData {
    private Card card;

    public StatsGalleryOutputData (Card card){
        this.card = card;

    }
    public Card getCard(){
        return card;
    }
}
