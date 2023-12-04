package use_case.lootbox;

public interface LootBoxOutputBoundary {


    void prepareSuccessView();

    void prepareFailView(String error);
}
