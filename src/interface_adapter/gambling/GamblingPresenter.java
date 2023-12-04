package interface_adapter.gambling;

import use_case.lootbox.LootBoxOutputBoundary;

public class GamblingPresenter implements LootBoxOutputBoundary {

    private final GamblingViewModel gamblingViewModel;

    public GamblingPresenter(GamblingViewModel gamblingViewModel){
        this.gamblingViewModel = gamblingViewModel;
    }




    @Override
    public void prepareSuccessView() {

    }

    @Override
    public void prepareFailView(String error){

    }
}
