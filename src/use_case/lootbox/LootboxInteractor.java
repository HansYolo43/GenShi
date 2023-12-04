package use_case.lootbox;

public class LootboxInteractor {

    private LootboxUserDataAccessInterface fileCardDataAccessObject;

    public LootboxInteractor(LootboxUserDataAccessInterface fileCardDataAccessObject){
        this.fileCardDataAccessObject = fileCardDataAccessObject;

    }

    public void execute(){
        Integer ID = fileCardDataAccessObject.randomcard();
        fileCardDataAccessObject.updateusercard(ID);

        LootBoxOutputData lootBoxOutputData = new LootBoxOutputData();

    }

}
