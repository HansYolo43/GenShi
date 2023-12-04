package use_case.cardStats;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class CardStatsInteractorTest {

    private CardStatsPresenterStub cardStatsPresenter;
    private CardStatsInteractor interactor;

    @Before
    public void setUp() {
        cardStatsPresenter = new CardStatsPresenterStub();
        interactor = new CardStatsInteractor(cardStatsPresenter);
    }

    @Test
    public void whenExecuteBackIsCalled_thenBackViewShouldBePrepared() {
        interactor.executeBack();
        assertTrue("Back view should be prepared", cardStatsPresenter.prepareBackViewCalled);
    }

    private static class CardStatsPresenterStub implements CardStatsOutputBoundary {
        private boolean prepareBackViewCalled = false;

        @Override
        public void prepareBackView() {
            prepareBackViewCalled = true;
        }
    }
}
