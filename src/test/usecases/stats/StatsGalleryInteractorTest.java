package use_case.StatsGallery;

import Entities.Card;
import Entities.User;
import data_access.FileCardDataAccessObject;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class StatsGalleryInteractorTest {

    private FileCardDataAccessObject fileCardDataAccessStub;
    private StatsGalleryInteractor interactor;

    @Before
    public void setUp() throws IOException {
        fileCardDataAccessStub =new FileCardDataAccessObject("test_cards.csv", "test/test_test.db");
        User user = fileCardDataAccessStub.getUser("TestUser");
        fileCardDataAccessStub.setActiveUser(user);
        interactor = new StatsGalleryInteractor(fileCardDataAccessStub);
    }

    @Test
    public void whenExecuteIsCalled_thenReturnCorrectCard() {
        Integer cardId = 1; // Sample card ID
        StatsGalleryInputData inputData = new StatsGalleryInputData(cardId);

        interactor.execute(inputData);
        Card retrievedCard = fileCardDataAccessStub.getCard(cardId);

        assertNotNull("Card should not be null", retrievedCard);
        assertEquals("Returned card ID should match the input card ID", cardId, retrievedCard.getId()); // Assuming Card has getId method
    }



}
