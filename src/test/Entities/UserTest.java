package Entities;

import org.junit.jupiter.api.*;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user;
    private final ArrayList<Integer> cardsOwned = new ArrayList<>();

    @BeforeEach
    void setUp() {
        cardsOwned.add(1);
        user = new User(1, cardsOwned, 2, 1000, "TestUser", "password");
    }

    @Test
    void testUserGettersAndSetters() {
        assertEquals(1, user.getUserid());
        assertEquals(2, user.getUserlevel());
        assertEquals(1000, user.getCurrency());
        assertEquals("TestUser", user.getUsername());
        assertEquals("password", user.getPassword());
        assertTrue(user.getCards_owned().contains(1));

        user.setUserid(2);
        user.setUserlevel(3);
        user.setCurrency(2000);
        user.setUsername("NewTestUser");
        user.setPassword("newpassword");
        user.addownedcard(2);

        assertEquals(2, user.getUserid());
        assertEquals(3, user.getUserlevel());
        assertEquals(2000, user.getCurrency());
        assertEquals("NewTestUser", user.getUsername());
        assertEquals("newpassword", user.getPassword());
        assertTrue(user.getownedcards().contains(2));
    }
}

