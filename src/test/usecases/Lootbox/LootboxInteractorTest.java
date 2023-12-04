package use_case.lootbox;

import Entities.Card;
import Entities.User;
import data_access.FileCardDataAccessObject;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class LootboxInteractorTest {

    private FileCardDataAccessObject userDataAccessStub;
    private LootBoxPresenterStub gamblingPresenter;
    private LootboxInteractor interactor;

    @Before
    public void setUp() throws IOException {
        userDataAccessStub =new FileCardDataAccessObject("test_cards.csv", "test/test_test.db");
        User user = userDataAccessStub.getUser("TestUser");
        userDataAccessStub.setActiveUser(user);
        gamblingPresenter = new LootBoxPresenterStub();
        interactor = new LootboxInteractor(userDataAccessStub, gamblingPresenter);
    }

    @Test
    public void whenExecuteIsCalled_thenCardShouldBeRetrievedAndSuccessViewPrepared() {
        interactor.execute();
        assertTrue(gamblingPresenter.prepareSuccessViewCalled);
        assertNotNull(gamblingPresenter.lootBoxOutputData.getCard());
    }

    @Test
    public void whenExecuteBackIsCalled_thenBackViewShouldBePrepared() {
        interactor.executeBack();
        assertTrue(gamblingPresenter.prepareBackViewCalled);
    }


    private static class LootBoxPresenterStub implements LootBoxOutputBoundary {
        private boolean prepareSuccessViewCalled = false;
        private boolean prepareBackViewCalled = false;
        private LootBoxOutputData lootBoxOutputData;

        @Override
        public void prepareSuccessView(LootBoxOutputData outputData) {
            this.prepareSuccessViewCalled = true;
            this.lootBoxOutputData = outputData;
        }

        @Override
        public void prepareBackView() {
            this.prepareBackViewCalled = true;
        }
    }
}

