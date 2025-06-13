package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.ClassicHttpResponse;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class LlmService {

    private static final String API_URL = "https://openrouter.ai/api/v1/chat/completions";
    private static final String API_KEY = "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";
    private static final String MODEL = "deepseek/deepseek-r1-0528-qwen3-8b:free";

    public static void main(String[] args) {
        try {
            String prompt = "¿Cuál es la capital de Francia?";
            String response = sendPrompt(prompt);
            System.out.println("Respuesta del modelo: " + response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String sendPrompt(String prompt) throws Exception {
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost request = new HttpPost(API_URL);

        request.setHeader("Authorization", "Bearer " + API_KEY);
        request.setHeader("Content-Type", "application/json");

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode requestBody = mapper.createObjectNode();
        requestBody.put("model", MODEL);

        ArrayNode messages = mapper.createArrayNode();
        ObjectNode userMessage = mapper.createObjectNode();
        userMessage.put("role", "user");
        userMessage.put("content", prompt);
        messages.add(userMessage);

        requestBody.set("messages", messages);

        StringEntity entity = new StringEntity(mapper.writeValueAsString(requestBody));
        request.setEntity(entity);

        ClassicHttpResponse response = (ClassicHttpResponse) httpClient.execute(request);
        BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        StringBuilder result = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            result.append(line);
        }

        ObjectNode jsonResponse = (ObjectNode) mapper.readTree(result.toString());
        return jsonResponse
                .withArray("choices")
                .get(0)
                .path("message")
                .path("content")
                .asText();
    }
}
