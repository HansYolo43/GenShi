package interface_adapter.gambling;

import interface_adapter.ViewManagerModel;
import use_case.lootbox.LootBoxOutputBoundary;
import use_case.lootbox.LootBoxOutputData;

public class GamblingPresenter implements LootBoxOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final GamblingViewModel gamblingViewModel;

    public GamblingPresenter(GamblingViewModel gamblingViewModel, ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
        this.gamblingViewModel = gamblingViewModel;
    }

    public void prepareSuccessView(LootBoxOutputData outputData) {
        GamblingState state = gamblingViewModel.getState();
        state.setName(outputData.getCard().getName());
        state.setRarity(outputData.getCard().getStats().getRarity());
        state.setDescription(outputData.getCard().getDesc());
        state.setImgpath(outputData.getCard().getimgpath());
        String path = outputData.getCard().getimgpath();
        path.replace("\\", "/");
        state.setImgpath(path);
        gamblingViewModel.setState(state);
        gamblingViewModel.firePropertyChanged();
    }

    public void prepareBackView() {
        viewManagerModel.setActiveView("main_menu");
        viewManagerModel.firePropertyChanged();
    }
}
