package data_access;

import org.junit.jupiter.api.*;
import Entities.Card;
import Entities.Stats;
import Entities.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class FileCardDataAccessObjectTest {

    private FileCardDataAccessObject fileCardDAO;
    private Card testCard;
    private User testUser;
    private Random random;

    @BeforeEach
    void setUp() throws IOException {
        fileCardDAO = new FileCardDataAccessObject("test_cards.csv", "test/test_test.db");

        testCard = createTestCard();
        testUser = new User(1, new ArrayList<>(), 1, 100, "TestUser1", "password");
        random = new Random();
    }

    private Card createTestCard() {
        return new Card(1, "Test Card", 101, "test/path", "Test Description", new Stats(1, "Test Affinity", 100, 50, 75, 10, "Common"));
    }

    @Test
    void testConstructor() {
        assertNotNull(fileCardDAO);
    }

    @Test
    void testAddAndGetCard() {
        fileCardDAO.addCard(testCard);
        assertEquals(testCard, fileCardDAO.getCard(testCard.getId()));
    }



    @Test
    void testGenerateUniqueId() {
        int uniqueId = fileCardDAO.generateUniqueId(random);
        assertNotNull(uniqueId);
        assertFalse(fileCardDAO.CardManager().contains(uniqueId));
    }

    @Test
    void testAddCardByInfo() {
        Integer newCardId = fileCardDAO.addCardbyinfo("New Card", "Description", "path", 1, "Affinity", 100, 50, 75, 10, "Rare");
        assertNotNull(newCardId);
        assertNotNull(fileCardDAO.getCard(newCardId));
    }

    @Test
    void testRemoveCard() {
        fileCardDAO.addCard(testCard);
        fileCardDAO.removeCard(testCard.getId());
        assertNull(fileCardDAO.getCard(testCard.getId()));
    }

    @Test
    void testSetAndGetActiveUser() {
        fileCardDAO.setActiveUser(testUser);
        assertEquals(testUser, fileCardDAO.getActiveUser());
    }

    @Test
    void testRandomCard() {
        fileCardDAO.addCard(testCard);
        System.out.println(fileCardDAO.randomcard());
        assertNotNull(fileCardDAO.randomcard());
    }

    @Test
    void testUpdateUserCard() {
        fileCardDAO.setActiveUser(testUser);
        fileCardDAO.updateusercard(testCard.getId());
        assertTrue(testUser.getownedcards().contains(testCard.getId()));
    }


    @Test
    void testLoadAllCards() {
        fileCardDAO.loadallcards();
        assertFalse(fileCardDAO.CardManager().isEmpty()); // Assuming there are cards in the database
    }



    @Test
    void testSetDescription() {
        fileCardDAO.addCard(testCard);
        fileCardDAO.setDescription(testCard.getId(), "New Description");
        assertEquals("New Description", fileCardDAO.getCard(testCard.getId()).getDesc());
    }

//    @Test
//    void testImageSave() {
//        // This test can be more of a placeholder as actual image saving involves external dependencies
//        assertDoesNotThrow(() -> fileCardDAO.imagesave("http://example.com/test.jpg", testCard));
//    }

    @Test
    void testExit() {
        // For exit, you can mainly test if no exception is thrown, as it's primarily a save operation
        assertDoesNotThrow(() -> fileCardDAO.exit());
    }

    @Test
    void testAddUser() {
        assertDoesNotThrow(() -> fileCardDAO.addUser(testUser));
        // Depending on your DatabaseHelper's implementation, you might check if the user is added
    }

}