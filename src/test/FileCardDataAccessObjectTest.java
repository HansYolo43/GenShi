package data_access;

import org.junit.jupiter.api.*;
import Entities.Card;
import Entities.Stats;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class FileCardDataAccessObjectTest {

    private FileCardDataAccessObject fileCardDAO;
    private Card testCard;
    private Random random;

    @BeforeEach
    void setUp() throws IOException {
        // Initialize with test data
        fileCardDAO = new FileCardDataAccessObject("test_cards.csv", "test_db.db");
        testCard = createTestCard();
        random = new Random();
    }

    private Card createTestCard() {
        return new Card(1, "Test Card", 101, "test/path", "Test Description", new Stats(1, "Test Affinity", 100, 50, 75, 10, "Common"));
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
    void testRemoveCard() {
        fileCardDAO.addCard(testCard);
        fileCardDAO.removeCard(testCard.getId());
        assertNull(fileCardDAO.getCard(testCard.getId()));
    }

    // Additional tests for methods like serialize, deserialize, updateCard, setDescription, etc.

    @AfterEach
    void tearDown() {
        // Clean up test data
        removeTestCardData();
    }

    private void removeTestCardData() {
        if (fileCardDAO.getCard(testCard.getId()) != null) {
            fileCardDAO.removeCard(testCard.getId());
        }
    }
}
