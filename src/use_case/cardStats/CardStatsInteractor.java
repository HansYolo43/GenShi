package use_case.cardStats;

public class CardStatsInteractor implements CardStatsInputBoundary {
    private final CardStatsOutputBoundary cardStatsPresenter;

    public CardStatsInteractor(CardStatsOutputBoundary cardStatsPresenter) {
        this.cardStatsPresenter = cardStatsPresenter;
    }

    @Override
    public void executeBack() {
        cardStatsPresenter.prepareBackView();
    }
}
