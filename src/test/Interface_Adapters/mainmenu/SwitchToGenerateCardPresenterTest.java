package interface_adapter.main_menu;

import interface_adapter.ViewManagerModel;
import interface_adapter.generatecard.GenerateCardViewModel;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class SwitchToGenerateCardPresenterTest {

    private MainMenuViewModel mainMenuViewModel;
    private GenerateCardViewModel generateCardViewModel;
    private ViewManagerModel viewManagerModel;
    private SwitchToGenerateCardPresenter presenter;

    @BeforeEach
    void setUp() {
        mainMenuViewModel = new MainMenuViewModel();
        generateCardViewModel = new GenerateCardViewModel();
        viewManagerModel = new ViewManagerModel();
        presenter = new SwitchToGenerateCardPresenter(mainMenuViewModel, generateCardViewModel, viewManagerModel);
    }

    @Test
    void testPrepareSuccessView() {
        presenter.prepareSuccessView();
        assertEquals(generateCardViewModel.getViewName(), viewManagerModel.getActiveView());
    }

    // The fail view test is omitted as SwitchToGenerateCardPresenter does not have a prepareFailView method
}
