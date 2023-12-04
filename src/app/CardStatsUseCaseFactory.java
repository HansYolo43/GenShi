package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.card_stats.CardStatsController;
import interface_adapter.card_stats.CardStatsPresenter;
import interface_adapter.card_stats.CardStatsViewModel;
import interface_adapter.gambling.GamblingController;
import interface_adapter.gambling.GamblingPresenter;
import interface_adapter.gambling.GamblingViewModel;
import use_case.cardStats.CardStatsInputBoundary;
import use_case.cardStats.CardStatsInteractor;
import use_case.cardStats.CardStatsOutputBoundary;
import use_case.lootbox.LootBoxInputBoundary;
import use_case.lootbox.LootBoxOutputBoundary;
import use_case.lootbox.LootboxInteractor;
import use_case.lootbox.LootboxUserDataAccessInterface;
import view.CardStatsView;
import view.GamblingView;

import java.io.IOException;

public class CardStatsUseCaseFactory {
    public static CardStatsView create(ViewManagerModel viewManagerModel, CardStatsViewModel cardStatsViewModel)  {
        CardStatsController cardStatsController = createCardStatsUseCase(viewManagerModel);
        return new CardStatsView(cardStatsViewModel, cardStatsController);
    }

    private static CardStatsController createCardStatsUseCase(ViewManagerModel viewManagerModel) {

        // Notice how we pass this method's parameters to the Presenter.
        CardStatsOutputBoundary cardStatsPresenter = new CardStatsPresenter(viewManagerModel);
        CardStatsInputBoundary cardStatsInteractor = new CardStatsInteractor(cardStatsPresenter);
        return new CardStatsController(cardStatsInteractor);
    }
}
