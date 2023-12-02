package use_case.StatsGallery;

import data_access.FileCardDataAccessObject;

public class StatsGallery {
    private final StatsGalleryDataAccessInterface fileCardDataAccessObject;

    public StatsGallery(FileCardDataAccessObject fileCardDataAccessObject){
        this.fileCardDataAccessObject = fileCardDataAccessObject;
    }

    public Entities.Card execute(Integer cardid){
         return fileCardDataAccessObject.getCard(cardid);
    }
}
