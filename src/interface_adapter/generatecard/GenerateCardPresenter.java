package interface_adapter.generatecard;

import Entities.Card;
import use_case.generatecard.GenerateCardOutputBoundary;

public class GenerateCardPresenter implements GenerateCardOutputBoundary {
    private GenerateCardViewModel viewModel;

    public GenerateCardPresenter(GenerateCardViewModel viewModel) {
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
}
