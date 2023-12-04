package use_case.main_menu;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class MainMenuInteractorsTest {

    private SwitchToGalleryPresenterStub switchToGalleryPresenter;
    private SwitchToGamblingPresenterStub switchToGamblingPresenter;
    private SwitchToGenerateCardPresenterStub switchToGenerateCardPresenter;

    private SwitchToGalleryInteractor switchToGalleryInteractor;
    private SwitchToGamblingInteractor switchToGamblingInteractor;
    private SwitchToGenerateCardInteractor switchToGenerateCardInteractor;

    @Before
    public void setUp() {
        switchToGalleryPresenter = new SwitchToGalleryPresenterStub();
        switchToGalleryInteractor = new SwitchToGalleryInteractor(switchToGalleryPresenter);

        switchToGamblingPresenter = new SwitchToGamblingPresenterStub();
        switchToGamblingInteractor = new SwitchToGamblingInteractor(switchToGamblingPresenter);

        switchToGenerateCardPresenter = new SwitchToGenerateCardPresenterStub();
        switchToGenerateCardInteractor = new SwitchToGenerateCardInteractor(switchToGenerateCardPresenter);
    }

    @Test
    public void whenSwitchToGalleryIsExecuted_thenSuccessViewShouldBePrepared() {
        switchToGalleryInteractor.execute();
        assertTrue("Switch to Gallery Success View should be prepared", switchToGalleryPresenter.prepareSuccessViewCalled);
    }

    @Test
    public void whenSwitchToGamblingIsExecuted_thenSuccessViewShouldBePrepared() {
        switchToGamblingInteractor.execute();
        assertTrue("Switch to Gambling Success View should be prepared", switchToGamblingPresenter.prepareSuccessViewCalled);
    }

    @Test
    public void whenSwitchToGenerateCardIsExecuted_thenSuccessViewShouldBePrepared() {
        switchToGenerateCardInteractor.execute();
        assertTrue("Switch to Generate Card Success View should be prepared", switchToGenerateCardPresenter.prepareSuccessViewCalled);
    }

    private static class SwitchToGalleryPresenterStub implements SwitchToGalleryOutputBoundary {
        private boolean prepareSuccessViewCalled = false;

        @Override
        public void prepareSuccessView() {
            prepareSuccessViewCalled = true;
        }

        @Override
        public void prepareFailView(String error) {

        }
    }

    private static class SwitchToGamblingPresenterStub implements SwitchToGamblingOutputBoundary {
        private boolean prepareSuccessViewCalled = false;

        @Override
        public void prepareSuccessView() {
            prepareSuccessViewCalled = true;
        }

        @Override
        public void prepareFailView(String error) {

        }
    }

    private static class SwitchToGenerateCardPresenterStub implements SwitchToGenerateCardOutputBoundary {
        private boolean prepareSuccessViewCalled = false;

        @Override
        public void prepareSuccessView() {
            prepareSuccessViewCalled = true;
        }
    }
}

