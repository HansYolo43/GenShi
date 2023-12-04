package api;

import data_access.FileCardDataAccessObject;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Random;


public class GenerateCardDataAccessInterFace {
    private final String apiKey;
    private final String themePrompt;

    private final FileCardDataAccessObject dataAccessObject;

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

    public String generateDescription(String characterName) throws IOException {
        String prompt = String.format("Generate a description for this" + characterName + "Be Brief and make sure you it not more than a few lines");
        System.out.println("desc");
        return makeRequest(createConnection(), createData(prompt));
    }

    public String generateRarity() {
        Random rand = new Random();
        double chance = rand.nextDouble();

        if (chance < 0.005) { // 0.5% chance for Mythic
            return "Mythic";
        } else if (chance < 0.015) { // Additional 1% chance for Legendary (1.5% - 0.5%)
            return "Legendary";
        } else if (chance < 0.065) { // Additional 5% chance for Epic (6.5% - 1.5%)
            return "Epic";
        } else if (chance < 0.265) { // Additional 20% chance for Rare (26.5% - 6.5%)
            return "Rare";
        } else {
            return "Common"; // All remaining chances
        }
    }

    public Integer generateAndSaveCharacters() throws IOException {

        ArrayList<String> Affinity = new ArrayList<>();
        Affinity.add("Fire- Additional damage and aggressive tactics.");
        Affinity.add("Water-Healing properties and fluid defense.");
        Affinity.add("Earth- Strong defense and stability.");
        Affinity.add("Air- Speed, evasion, and unpredictable movements.");
        Affinity.add("Stealthv- Evade detection and gain surprise advantages.");
        Affinity.add("Deception- Mislead opponents and disrupt their strategies.");
        Affinity.add("Surprise Attack- Strike first and unexpectedly.");
        Affinity.add("Spellcasting- Cast powerful spells.");
        Affinity.add("Enchantment- Enhance abilities of other cards.");
        Affinity.add("Mystical Manipulation- Alter the course of the game.");


        Random rand = new Random();


        String characterName = generateName(String.format("Generate a unique %s themed character name:",
                themePrompt));
        String affinity = Affinity.get(new Random().nextInt(Affinity.size()));
        int level = rand.nextInt(30) + 1;
        int basehp = rand.nextInt(100) + 1;
        int basedef = rand.nextInt(100) + 1;
        int baseatk = rand.nextInt(100) + 1;
        int basecrit = rand.nextInt(100) + 1;

        String description = generateDescription("Generate a description for this" + characterName + "Be Brief and make sure you it not more than a few lines");

        String rarity = generateRarity();

        return dataAccessObject.addCardbyinfo(characterName, description, "Not Assigned", level, affinity, basehp, basedef, baseatk, basecrit, rarity);

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

    public JSONObject createData(String prompt) {
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

        data.put("model", "gpt-3.5-turbo");
        data.put("messages", messages);
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
