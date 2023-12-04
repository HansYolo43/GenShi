package api;

import data_access.FileCardDataAccessObject;
import org.json.JSONObject;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.net.HttpURLConnection;

import static org.junit.jupiter.api.Assertions.*;

class GenerateImageDataAccessInterfaceTest {

    private GenerateImageDataAccessInterface generateImageInterface;
    private FileCardDataAccessObject dataAccessObject;

    @BeforeEach
    void setUp() throws IOException {
        dataAccessObject = new FileCardDataAccessObject("test_cards.csv", "test_db.db");
        generateImageInterface = new GenerateImageDataAccessInterface("apiKey", dataAccessObject);
    }

    @Test
    void testGenerateImageForCard() {
        Exception exception = assertThrows(IOException.class, () -> {
            generateImageInterface.generateImageForCard(1, "prompt");
        });
        String expectedMessage = "Server returned HTTP response code: 401 for URL: https://api.openai.com/v1/images/generations";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

}

