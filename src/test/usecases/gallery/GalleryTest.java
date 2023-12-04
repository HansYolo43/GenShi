package usecases.gallery;



import Entities.Card;
import Entities.User;
import data_access.FileCardDataAccessObject;
import org.junit.Before;
import org.junit.Test;
import use_case.gallery.Gallery;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GalleryTest {

    private FileCardDataAccessObject fileCardDataAccessStub;
    private Gallery gallery;

    @Before
    public void setUp() throws IOException {
        fileCardDataAccessStub =new FileCardDataAccessObject("test_cards.csv", "test/test_test.db");
        User user = fileCardDataAccessStub.getUser("TestUser");
        fileCardDataAccessStub.setActiveUser(user);
        gallery = new Gallery(fileCardDataAccessStub);
    }


    @Test
    public void whenExecuteIsCalled_thenReturnsCardHashMap() {
        HashMap<Card, Boolean> result = gallery.execute();
        assertFalse("Result should not be empty", result.isEmpty());
        assertTrue("Result should contain specific card status", result.containsKey(fileCardDataAccessStub.getCard(1))); // Example check
    }




}
