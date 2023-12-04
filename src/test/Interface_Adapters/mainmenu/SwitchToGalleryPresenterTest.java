package interface_adapter.main_menu;

import interface_adapter.ViewManagerModel;
import interface_adapter.gallery.GalleryViewModel;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class SwitchToGalleryPresenterTest {

    private MainMenuViewModel mainMenuViewModel;
    private GalleryViewModel galleryViewModel;
    private ViewManagerModel viewManagerModel;
    private SwitchToGalleryPresenter presenter;

    @BeforeEach
    void setUp() {
        mainMenuViewModel = new MainMenuViewModel();
        galleryViewModel = new GalleryViewModel();
        viewManagerModel = new ViewManagerModel();
        presenter = new SwitchToGalleryPresenter(viewManagerModel, mainMenuViewModel, galleryViewModel);
    }

    @Test
    void testPrepareSuccessView() {
        presenter.prepareSuccessView();
        assertEquals(galleryViewModel.getViewName(), viewManagerModel.getActiveView());
    }

    @Test
    void testPrepareFailView() {
        String error = "Gallery Error";
        presenter.prepareFailView(error);
        assertEquals(error, mainMenuViewModel.getState().getGalleryError());
    }
}
