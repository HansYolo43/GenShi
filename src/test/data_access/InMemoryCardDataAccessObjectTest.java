package data_access;

import Entities.Card;
import Entities.Stats;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class InMemoryCardDataAccessObjectTest {

    private InMemoryCardDataAccessObject dao;
    private Card testCard;

    @BeforeEach
    void setUp() {
        dao = new InMemoryCardDataAccessObject();
        testCard = new Card(1, "Test Card", 101, "Test Description", "test/path", new Stats(1, "Test Affinity", 100, 50, 75, 10, "Common"));
    }

    @Test
    void testAddAndGetCard() {
        dao.addCard(testCard);
        assertEquals(testCard, dao.getCard(testCard.getId()));
    }


    @Test
    void testSetDescription() {
        dao.addCard(testCard);
        dao.setDescription(testCard.getId(), "New Description");
        assertEquals("New Description", dao.getCard(testCard.getId()).getDesc());
    }

    @Test
    void testRemoveCard() {
        dao.addCard(testCard);
        dao.removeCard(testCard.getId());
        assertNull(dao.getCard(testCard.getId()));
    }

    @Test
    void testGetAllCards() {
        dao.addCard(testCard);
        Map<Integer, Card> allCards = dao.getAllCards();
        assertTrue(allCards.containsKey(testCard.getId()) && allCards.containsValue(testCard));
    }
}
