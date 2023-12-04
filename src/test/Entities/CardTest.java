package Entities;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    private Card card;
    private Stats stats;

    @BeforeEach
    void setUp() {
        stats = new Stats(1, "Fire", 100, 50, 75, 10, "Common");
        card = new Card(1, "Test Card", 101, "Test Description", "test/path", stats);
    }

    @Test
    void testCardGettersAndSetters() {
        assertEquals(1, card.getId());
        assertEquals("Test Card", card.getName());
        assertEquals(101, card.getImageID());
        assertEquals("Test Description", card.getDesc());
        assertEquals("test/path", card.getimgpath());
        assertEquals(stats, card.getStats());

        card.setName("Updated Test Card");
        card.setDesc("New Description");
        card.setImgpath("new/path");

        assertEquals("Updated Test Card", card.getName());
        assertEquals("New Description", card.getDesc());
        assertEquals("new/path", card.getimgpath());
    }
}
