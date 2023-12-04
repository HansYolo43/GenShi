package interface_adapter.card_stats;

import use_case.cardStats.CardStatsInputBoundary;

public class CardStatsController {
    final CardStatsInputBoundary cardStatsInteractor;

    public CardStatsController(CardStatsInputBoundary cardStatsInteractor) {
        this.cardStatsInteractor = cardStatsInteractor;
    }

//    public void execute() {
//        cardStatsInteractor.execute();
//    }

    public void executeBack() {
        cardStatsInteractor.executeBack();
    }
}
