package use_case.lootbox;

import Entities.Card;

public class LootboxInteractor implements LootBoxInputBoundary{

    private LootboxUserDataAccessInterface fileCardDataAccessObject;
    private final LootBoxOutputBoundary gamblingPresenter;

    public LootboxInteractor(LootboxUserDataAccessInterface fileCardDataAccessObject, LootBoxOutputBoundary gamblingPresenter) {
        this.fileCardDataAccessObject = fileCardDataAccessObject;
        this.gamblingPresenter = gamblingPresenter;
    }

    public void execute(){
        Integer ID = fileCardDataAccessObject.randomcard();
        fileCardDataAccessObject.updateusercard(ID);
        Card card = fileCardDataAccessObject.getCard(ID);

        LootBoxOutputData lootBoxOutputData = new LootBoxOutputData(card);
        gamblingPresenter.prepareSuccessView(lootBoxOutputData);
    }

    public void executeBack(){
        gamblingPresenter.prepareBackView();
    }
}
