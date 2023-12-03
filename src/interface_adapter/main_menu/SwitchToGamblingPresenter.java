package interface_adapter.main_menu;


import interface_adapter.ViewManagerModel;
import interface_adapter.gambling.GamblingViewModel;
import use_case.main_menu.SwitchToGamblingOutputBoundary;

public class SwitchToGamblingPresenter implements SwitchToGamblingOutputBoundary {
    private final MainMenuViewModel mainMenuViewModel;
    private final GamblingViewModel gamblingViewModel;
    private final ViewManagerModel viewManagerModel;

    public SwitchToGamblingPresenter(MainMenuViewModel mainMenuViewModel, GamblingViewModel gamblingViewModel, ViewManagerModel viewManagerModel) {
        this.mainMenuViewModel = mainMenuViewModel;
        this.gamblingViewModel = gamblingViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView() {
        this.mainMenuViewModel.setState(mainMenuViewModel.getState());
        mainMenuViewModel.firePropertyChanged();
        // Fixed the issue here.
        viewManagerModel.setActiveView(gamblingViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        MainMenuState mainMenuState = mainMenuViewModel.getState();
        mainMenuState.setGamblingError(error);
        mainMenuViewModel.firePropertyChanged();
    }
}