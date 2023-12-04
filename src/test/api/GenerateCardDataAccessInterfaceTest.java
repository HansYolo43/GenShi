package api;

import data_access.FileCardDataAccessObject;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class GenerateCardDataAccessInterfaceTest{
    private GenerateCardDataAccessInterFace generateCardDataAccessInterFace;

    private FileCardDataAccessObject fileCardDAO;


    @Before
    public void setUp() throws IOException {
        fileCardDAO = new FileCardDataAccessObject("test_cards.csv", "test/test_test.db");
        generateCardDataAccessInterFace = new GenerateCardDataAccessInterFace("testApiKey", "testThemePrompt", fileCardDAO);

    }

    @Test
    public void testGenerateRarity() {
        String rarity = generateCardDataAccessInterFace.generateRarity();
        assertNotNull("Rarity should not be null", rarity);
        assertTrue("Rarity should be one of the predefined types",
                rarity.equals("Mythic") || rarity.equals("Legendary") || rarity.equals("Epic") || rarity.equals("Rare") || rarity.equals("Common"));
    }
    @Test
    public void testCreateData() {
        GenerateCardDataAccessInterFace dataAccess = new GenerateCardDataAccessInterFace("apiKey", "themePrompt", null);

        String testPrompt = "Test prompt";
        JSONObject result = dataAccess.createData(testPrompt);

        assertNotNull("Data object should not be null", result);
        assertEquals("Model should be gpt-3.5-turbo", "gpt-3.5-turbo", result.getString("model"));

        JSONArray messages = result.getJSONArray("messages");
        assertNotNull("Messages array should not be null", messages);
        assertEquals("Messages array should contain two messages", 2, messages.length());

        JSONObject systemMessage = messages.getJSONObject(0);
        assertEquals("First message should be from system", "system", systemMessage.getString("role"));
        assertEquals("System message content should match", "You are a helpful assistant.", systemMessage.getString("content"));

        JSONObject userMessage = messages.getJSONObject(1);
        assertEquals("Second message should be from user", "user", userMessage.getString("role"));
        assertEquals("User message content should match test prompt", testPrompt, userMessage.getString("content"));
    }
}



    // Write similar tests for other methods
    // Note: For methods like generateName, generateDescription, and generateAndSaveCharacters,
    // you might need to override the network call logic for proper unit testing
