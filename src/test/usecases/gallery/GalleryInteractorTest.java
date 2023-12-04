package use_case.gallery;

import Entities.Card;
import Entities.Stats;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class GalleryInteractorTest {

    private GalleryPresenterStub galleryPresenter;
    private GalleryInteractor interactor;

    @Before
    public void setUp() {
        galleryPresenter = new GalleryPresenterStub();
        interactor = new GalleryInteractor(galleryPresenter);
    }

    @Test
    public void whenExecuteIsCalled_thenSuccessViewShouldBePrepared() {
        Card card = new Card(1, "Test Card", 101, "test/path", "Test Description", new Stats(1, "Test Affinity", 100, 50, 75, 10, "Common"));
        interactor.execute(card);
        assertTrue("Success view should be prepared", galleryPresenter.prepareSuccessViewCalled);
    }

    @Test
    public void whenExecuteBackIsCalled_thenBackViewShouldBePrepared() {
        interactor.executeBack();
        assertTrue("Back view should be prepared", galleryPresenter.prepareBackViewCalled);
    }

    private static class GalleryPresenterStub implements GalleryOutputBoundary {
        private boolean prepareSuccessViewCalled = false;
        private boolean prepareBackViewCalled = false;

        @Override
        public void prepareSuccessView(Card card) {
            prepareSuccessViewCalled = true;
        }

        @Override
        public void prepareBackView() {
            prepareBackViewCalled = true;
        }
    }
}
