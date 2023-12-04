package interface_adapter.main_menu;

import interface_adapter.ViewManagerModel;
import interface_adapter.gambling.GamblingViewModel;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class SwitchToGamblingPresenterTest {

    private MainMenuViewModel mainMenuViewModel;
    private GamblingViewModel gamblingViewModel;
    private ViewManagerModel viewManagerModel;
    private SwitchToGamblingPresenter presenter;

    @BeforeEach
    void setUp() {
        mainMenuViewModel = new MainMenuViewModel();
        gamblingViewModel = new GamblingViewModel();
        viewManagerModel = new ViewManagerModel();
        presenter = new SwitchToGamblingPresenter(mainMenuViewModel, gamblingViewModel, viewManagerModel);
    }

    @Test
    void testPrepareSuccessView() {
        presenter.prepareSuccessView();
        assertEquals(gamblingViewModel.getViewName(), viewManagerModel.getActiveView());
    }


}
