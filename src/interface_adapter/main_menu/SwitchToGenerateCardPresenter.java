package interface_adapter.main_menu;

import interface_adapter.ViewManagerModel;
import interface_adapter.generatecard.GenerateCardViewModel;
import use_case.main_menu.SwitchToGenerateCardOutputBoundary;
import view.GenerateCardView;
import view.MainMenuView;

public class SwitchToGenerateCardPresenter implements SwitchToGenerateCardOutputBoundary {
    private final MainMenuViewModel mainMenuViewModel;
    private final GenerateCardViewModel generateCardViewModel;
    private final ViewManagerModel viewManagerModel;

    public SwitchToGenerateCardPresenter(MainMenuViewModel mainMenuViewModel, GenerateCardViewModel generateCardViewModel, ViewManagerModel viewManagerModel) {
        this.mainMenuViewModel = mainMenuViewModel;
        this.generateCardViewModel = generateCardViewModel;
        this.viewManagerModel = viewManagerModel;
    }
    @Override
    public void prepareSuccessView() {
        mainMenuViewModel.setState(mainMenuViewModel.getState());
        mainMenuViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(generateCardViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
        System.out.println("shits actually supposed to happen here");
    }
}
