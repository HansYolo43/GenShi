package interface_adapter.card_stats;

import interface_adapter.ViewManagerModel;
import use_case.cardStats.CardStatsOutputBoundary;

public class CardStatsPresenter implements CardStatsOutputBoundary {
    private final ViewManagerModel viewManagerModel;

    public CardStatsPresenter(ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
    }

    public void prepareBackView() {
        viewManagerModel.setActiveView("gallery");
        viewManagerModel.firePropertyChanged();
    }
}
