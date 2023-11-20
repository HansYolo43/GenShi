package use_case.generatecard;

import data_access.FileCardDataAccessObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONObject;


public class GenerateCardDataAccessInterFace {
    private final String apiKey;
    private final String themePrompt;

    private FileCardDataAccessObject dataAccessObject;

    public GenerateCardDataAccessInterFace(String apiKey, String themePrompt, FileCardDataAccessObject dataAccessObject) {
        this.apiKey = apiKey;
        this.themePrompt = themePrompt;
        this.dataAccessObject = dataAccessObject;
    }

    public String generateName(String prompt) throws IOException {

        System.out.println("name");
        HttpURLConnection con = createConnection();
        JSONObject data = createData(prompt);
        return makeRequest(con, data);
    }

    public String generateDescription(String characterName, String attackName, String defenseName) throws IOException {
        String prompt = String.format("Generate a visual representation of unique %s character from %s in very few words:", characterName,  themePrompt);
        System.out.println("desc");
        return makeRequest(createConnection(), createData(prompt));
    }

    public Integer generateAndSaveCharacters() throws IOException {

        Random rand = new Random();



        String characterName = generateName(String.format("Generate a unique %s themed character name:",
                themePrompt));
        String attackName = generateName(String.format("Generate a unique %s themed attack name:", themePrompt));
        String defenseName = generateName(String.format("Generate a unique %s themed defense name:", themePrompt));
        String affinity = generateName(String.format("Generate a unique  %s affinity", themePrompt));
        int level = rand.nextInt(30) + 1;
        int basehp = rand.nextInt(100) + 1;
        int basedef = rand.nextInt(100) + 1;
        int baseatk = rand.nextInt(100) + 1;
        int basecrit = rand.nextInt(100) + 1;
        String description = generateDescription(characterName, attackName, defenseName);


        return dataAccessObject.addCardbyinfo(characterName, description, "Not Assigned", level, affinity, basehp, basedef, baseatk, basecrit);

    }

    private HttpURLConnection createConnection() throws IOException {
        URL url = new URL("https://api.openai.com/v1/chat/completions");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization", "Bearer " + apiKey);
        con.setDoOutput(true);
        return con;
    }

    private JSONObject createData(String prompt) {
        JSONObject data = new JSONObject();
        JSONArray messages = new JSONArray();

        // System message to set the context
        JSONObject systemMessage = new JSONObject();
        systemMessage.put("role", "system");
        systemMessage.put("content", "You are a helpful assistant.");

        // User message with the prompt
        JSONObject userMessage = new JSONObject();
        userMessage.put("role", "user");
        userMessage.put("content", prompt);

        messages.put(systemMessage);
        messages.put(userMessage);

        data.put("model", "gpt-4");
        data.put("messages", messages);
        return data;
    }


    private String makeRequest(HttpURLConnection con, JSONObject data) throws IOException {
        try (OutputStream os = con.getOutputStream()) {
            byte[] input = data.toString().getBytes("utf-8");
            os.write(input, 0, input.length);
        }


        try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine;

            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            JSONObject jsonResponse = new JSONObject(response.toString());
            System.out.println(jsonResponse);
            JSONArray choices = jsonResponse.getJSONArray("choices");
            if (!choices.isEmpty()) {
                JSONObject choice = choices.getJSONObject(0);
                if (choice.has("message")) {
                    JSONObject message = choice.getJSONObject("message");
                    return message.getString("content");
                }
            }
            return null; // Or handle appropriately if no valid response is found
        }
    }

}
