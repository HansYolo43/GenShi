package interface_adapter.main_menu;

import interface_adapter.ViewManagerModel;
import use_case.main_menu.SwitchToGalleryOutputBoundary;

public class SwitchToGalleryPresenter implements SwitchToGalleryOutputBoundary {
    private final MainMenuViewModel mainMenuViewModel;
    private final GalleryViewModel galleryViewModel;
    private final ViewManagerModel viewManagerModel;

    public SwitchToGalleryPresenter(MainMenuViewModel mainMenuViewModel, GalleryViewModel galleryViewModel, ViewManagerModel viewManagerModel) {
        this.mainMenuViewModel = mainMenuViewModel;
        this.galleryViewModel = galleryViewModel;
        this.viewManagerModel = viewManagerModel;
    }
    @Override
    public void prepareSuccessView() {
        this.mainMenuViewModel.setState(mainMenuViewModel.getState());
        mainMenuViewModel.firePropertyChanged();
        // Fixed the issue here.
        viewManagerModel.setActiveView(galleryViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        MainMenuState mainMenuState = mainMenuViewModel.getState();
        mainMenuState.setGalleryError(error);
        mainMenuViewModel.firePropertyChanged();
    }
}
