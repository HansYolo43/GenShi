package interface_adapter.gambling;

import use_case.lootbox.LootBoxInputBoundary;

public class GamblingController {
    final LootBoxInputBoundary gamblingInteractor;

    public GamblingController(LootBoxInputBoundary gamblingInteractor) {
        this.gamblingInteractor = gamblingInteractor;
    }

    public void execute() {
        gamblingInteractor.execute();
    }

    public void executeBack() {
        gamblingInteractor.executeBack();
    }
}