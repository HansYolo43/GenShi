package api;

import Entities.Card;
import data_access.FileCardDataAccessObject;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class GenerateImageDataAccessInterface {
    private final String apiKey;
    private final FileCardDataAccessObject dataAccessObject;


    public GenerateImageDataAccessInterface(String apiKey, FileCardDataAccessObject dataAccessObject) {
        this.apiKey = apiKey;
        this.dataAccessObject = dataAccessObject;
    }

    public void generateImageForCard(int cardId, String prompt) throws IOException {
        String imagelink = requestImageFromDallE("Generate a simple character card"  + prompt);
        saveImage(imagelink, cardId);
    }

    private String requestImageFromDallE(String prompt) throws IOException {
        // Implement the API request to DALL-E 3 here
        // For example, create a connection to the DALL-E 3 API endpoint
        // Send the prompt and handle the response
        // The response should include a URL or binary data for the generated image
        // Return the URL or a path where the image is saved
        URL url = new URL("https://api.openai.com/v1/images/generations"); // DALL-E image generation endpoint
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization", "Bearer " + apiKey);
        con.setDoOutput(true);

        JSONObject data = createData(prompt);
        return makeRequest(con, data); // This should now handle the response to e

    }


    private void saveImage(String imagePath, int cardId) throws IOException {
        // Save the image to a local directory
        // Update the card object with the image path
        Card card = dataAccessObject.getCard(cardId);


        if (card != null) {
            dataAccessObject.imagesave(imagePath, card);
        }
    }


    // Other methods as needed, e.g., to handle HTTP connection, read/write image data, etc.

    private HttpURLConnection createConnection() throws IOException {
        URL url = new URL("https://api.openai.com/v1/completions");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization", "Bearer " + apiKey);
        con.setDoOutput(true);
        return con;
    }

    private JSONObject createData(String prompt) {
        JSONObject data = new JSONObject();
        data.put("prompt", prompt);
        data.put("n", 1); // Number of images to generate
        data.put("size", "1024x1024"); // Size of the generated image
        data.put("model", "dall-e-3"); // Specify the model to use
        // Include any other necessary parameters as per the DALL-E API documentation
        return data;
    }

    private String makeRequest(HttpURLConnection con, JSONObject data) throws IOException {
        try (OutputStream os = con.getOutputStream()) {
            byte[] input = data.toString().getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }

            System.out.println(response);
            return new JSONObject(response.toString()).getJSONArray("data").getJSONObject(0).getString("url").trim();
        }
    }
}

