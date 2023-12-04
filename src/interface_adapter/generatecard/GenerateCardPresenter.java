package interface_adapter.generatecard;

import Entities.Card;
import interface_adapter.ViewManagerModel;
import use_case.generatecard.GenerateCardOutputBoundary;

public class GenerateCardPresenter implements GenerateCardOutputBoundary {
    private ViewManagerModel viewManagerModel;
    private GenerateCardViewModel viewModel;

    public GenerateCardPresenter(ViewManagerModel viewManagerModel, GenerateCardViewModel viewModel) {
        this.viewManagerModel = viewManagerModel;
        this.viewModel = viewModel;
    }

    @Override
    public void presentGeneratedCard(Card card) {
        GenerateCardState state = viewModel.getState();
        state.setGeneratedCard(card);
        viewModel.setState(state);
    }

    @Override
    public void presentError(String error) {
        GenerateCardState state = viewModel.getState();
        state.setError(error);
        viewModel.setState(state);
    }

    public void prepareBackView(){
        viewManagerModel.setActiveView("main_menu");
        viewManagerModel.firePropertyChanged();
    }
}
