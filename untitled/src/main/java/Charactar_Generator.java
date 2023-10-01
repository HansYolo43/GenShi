import org.json.JSONObject;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

public class Character_Generator {
    private final String apiKey;
    private final String themePrompt;

    public Character_Generator(String apiKey, String themePrompt) {
        this.apiKey = apiKey;
        this.themePrompt = themePrompt;
    }

    public String generateName(String prompt) throws IOException {
        HttpURLConnection con = createConnection();
        JSONObject data = createData(prompt, 50);
        return makeRequest(con, data);
    }

    public String generateDescription(String characterName, String attackName, String defenseName, int defenseStat, int attackStat1, int attackStat2) throws IOException {
        String prompt = String.format("Describe a character named %s with %s defense stat %d and attack names %s with stats %d or %d in the %s theme:", characterName, defenseName, defenseStat, attackName, attackStat1, attackStat2, themePrompt);
        HttpURLConnection con = createConnection();
        JSONObject data = createData(prompt, 100);
        return makeRequest(con, data);
    }

    public void generateAndSaveCharacters(int numCharacters, String filePath) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        Random rand = new Random();
        for (int i = 0; i < numCharacters; i++) {
            String characterName = generateName(String.format("Generate a unique %s themed character name:", themePrompt));
            String attackName = generateName(String.format("Generate a unique %s themed attack name:", themePrompt));
            String defenseName = generateName(String.format("Generate a unique %s themed defense name:", themePrompt));
            int defenseStat = rand.nextInt(100) + 1;
            int attackStat1 = rand.nextInt(100) + 1;
            int attackStat2 = rand.nextInt(100) + 1;
            String description = generateDescription(characterName, attackName, defenseName, defenseStat, attackStat1, attackStat2);
            writer.write(String.format("Character Name: %s\nAttack Name: %s\nDefense Name: %s\nDefense Stat: %d\nAttack Stat Options: %d or %d\nCharacter Description: %s\n\n", characterName, attackName, defenseName, defenseStat, attackStat1, attackStat2, description));
        }
        writer.close();
    }

    private HttpURLConnection createConnection() throws IOException {
        URL url = new URL("https://api.openai.com/v1/completions");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization", "Bearer " + apiKey);
        con.setDoOutput(true);
        return con;
    }

    private JSONObject createData(String prompt, int maxTokens) {
        JSONObject data = new JSONObject();
        data.put("prompt", prompt);
        data.put("max_tokens", maxTokens);
        data.put("temperature", 0.7);
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
            return new JSONObject(response.toString()).getJSONArray("choices").getJSONObject(0).getString("text").trim();
        }
    }

    public static void main(String[] args) throws IOException {
        String apiKey = "your-api-key";  // Replace with your API Key
        String themePrompt = "Cyberpunk";
        Character_Generator generator = new Character_Generator(apiKey, themePrompt);
        generator.generateAndSaveCharacters(50, "path-to-your-output-file.txt");  // Replace with the path where you want to save the characters
    }
}
