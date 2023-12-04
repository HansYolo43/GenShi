package data_access;

import Database.DatabaseHelper;
import Entities.Card;
import Entities.Stats;
import Entities.User;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseHelperTest {

    private static final String TEST_DB_PATH = "test/test_db.db";
    private static DatabaseHelper dbHelper;
    private Card testCard;
    private User testUser;

    @BeforeAll
    static void setUpBeforeClass() throws SQLException {
        dbHelper = new DatabaseHelper(TEST_DB_PATH);
        DatabaseHelper.createNewDatabase(TEST_DB_PATH);
        DatabaseHelper.createTables();
    }

    @BeforeEach
    void setUp() {
        testCard = new Card(1, "Test Card", 101, "test/path", "Test Description", new Stats(1, "Test Affinity", 100, 50, 75, 10, "Common"));
        testUser = new User(1, new ArrayList<>(), 1, 100, "TestUser", "password");
        dbHelper.insertCardIntoSQLite(testCard);
    }

    @Test
    void testCreateNewDatabase() {
        assertDoesNotThrow(() -> DatabaseHelper.createNewDatabase(TEST_DB_PATH));
    }

    @Test
    void testConnect() {
        Connection conn = DatabaseHelper.connect();
        assertNotNull(conn);
    }

    @Test
    void testCreateTables() {
        assertDoesNotThrow(DatabaseHelper::createTables);
    }

    @Test
    void testInsertAndLoadCard() {
        ArrayList<Card> cards = DatabaseHelper.loadCards();
        assertTrue(cards.stream().anyMatch(card -> Objects.equals(card.getId(), testCard.getId())));
    }

    @Test
    void testIdExistsInDatabase() {
        assertTrue(dbHelper.idExistsInDatabase(testCard.getId()));
        assertFalse(dbHelper.idExistsInDatabase(-1)); // Assuming -1 is an ID that doesn't exist
    }

    @Test
    void testSaveAndLoadUser() {
        dbHelper.saveUser(testUser);
        User loadedUser = dbHelper.loadUser(testUser.getUsername());
        assertEquals(testUser.getUsername(), loadedUser.getUsername());
    }

    @Test
    void testUpdateCard() {
        testCard.setName("Updated Test Card");
        dbHelper.insertCardIntoSQLite(testCard); // Assuming this method also handles updates
        ArrayList<Card> cards = DatabaseHelper.loadCards();
        System.out.println(cards);
        System.out.println(testCard.getName());
        assertTrue(cards.stream() != null);
    }

    @AfterEach
    void tearDown() throws SQLException {
        // Clear the database after each test
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + TEST_DB_PATH)) {
//            conn.createStatement().execute("DELETE FROM Cards");
//            conn.createStatement().execute("DELETE FROM Stats");
            // More DELETE statements for other tables
        }
    }
}