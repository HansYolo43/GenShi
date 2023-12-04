package interface_adapter.main_menu;

import interface_adapter.ViewManagerModel;
import interface_adapter.gallery.GalleryViewModel;
import use_case.main_menu.SwitchToGalleryOutputBoundary;
import view.GalleryView;

public class SwitchToGalleryPresenter implements SwitchToGalleryOutputBoundary {
    private final MainMenuViewModel mainMenuViewModel;
    private final GalleryViewModel galleryViewModel;
    private final ViewManagerModel viewManagerModel;

    public SwitchToGalleryPresenter(ViewManagerModel viewManagerModel, MainMenuViewModel mainMenuViewModel, GalleryViewModel galleryViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.mainMenuViewModel = mainMenuViewModel;
        this.galleryViewModel = galleryViewModel;
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
        mainMenuViewModel.firePropertyChanged();
    }
}
