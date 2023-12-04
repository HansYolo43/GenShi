package use_case.StatsGallery;

import Entities.Card;
import data_access.FileCardDataAccessObject;
import use_case.cardStats.CardStatsInputBoundary;

public class StatsGalleryInteractor {
    private StatsGalleryDataAccessInterface fileCardDataAccessObject = null;

    public StatsGalleryInteractor(FileCardDataAccessObject fileCardDataAccessObject){
        this.fileCardDataAccessObject = fileCardDataAccessObject;
    }

    public void execute(StatsGalleryInputData statsGalleryInputData){
        Integer cardid = statsGalleryInputData.getCardID();

        Card card = fileCardDataAccessObject.getCard(cardid);

        StatsGalleryOutputData statsGalleryOutputData = new StatsGalleryOutputData(card);

    }
}
