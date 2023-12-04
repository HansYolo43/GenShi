package Entities;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class StatsTest {

    private Stats stats;

    @BeforeEach
    void setUp() {
        stats = new Stats(1, "Fire", 100, 50, 75, 10, "Common");
    }

    @Test
    void testStatsGettersAndSetters() {
        assertEquals(1, stats.getLevel());
        assertEquals("Fire", stats.getAffinity());
        assertEquals(100, stats.getBaseHP());
        assertEquals(50, stats.getBaseDEF());
        assertEquals(75, stats.getBaseATK());
        assertEquals(10, stats.getBaseCRIT());
        assertEquals("Common", stats.getRarity());

        stats.setLevel(2);
        stats.setRarity("Rare");

        assertEquals(2, stats.getLevel());
        assertEquals("Rare", stats.getRarity());
    }
}