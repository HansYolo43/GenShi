package Entities;

import org.junit.jupiter.api.*;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class CardManagerTest {

    private CardManager cardManager;
    private Card card;

    @BeforeEach
    void setUp() {
        ArrayList<Integer> deck = new ArrayList<>();
        deck.add(1);
        cardManager = new CardManager(deck);
        card = new Card(1, "Test Card", 101, "Test Description", "test/path", new Stats(1, "Test Affinity", 100, 50, 75, 10, "Common"));
    }

    @Test
    void testCardManager() {
        cardManager.addCard(card);
        assertEquals(1, cardManager.getSize());
        assertEquals(card, cardManager.getCard(0));
        assertEquals("Test Card", cardManager.getCards().get(0).getName());

        cardManager.removeCard(card);
        assertEquals(0, cardManager.getSize());
    }
}
