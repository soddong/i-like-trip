package com.ssafy.chatgpt;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@RequestMapping("/gpt")
public class GptController {
    private static final String API_KEY = "sk-proj-GNX9uIgvK9DrQwby0HgkT3BlbkFJJk4BhRO58Rw45nmVGZBA";
    private static final String API_URL = "https://api.openai.com/v1/chat/completions";

    @GetMapping
    public String getGptResponse() {
        try {
            System.out.println("hi");
            HttpClient client = HttpClient.newHttpClient();

            // JSON 요청 본문 생성
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode requestBody = objectMapper.createObjectNode();
            requestBody.put("model", "gpt-3.5-turbo");
            requestBody.put("max_tokens", 100);
            requestBody.put("temperature", 1);

            ObjectNode message = objectMapper.createObjectNode();
            message.put("role", "user");
            message.put("content", "3개월간(24년1월 ~ 24년4월) 가장 인기있었던 국내 여행지 top5 를 알려줘");

            requestBody.set("messages", objectMapper.createArrayNode().add(message));

            // HTTP 요청 생성
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(API_URL))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + API_KEY)
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody.toString()))
                    .build();

            // 요청 보내기 및 응답 받기
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                // 응답 처리
                JsonNode responseJson = objectMapper.readTree(response.body());
                JsonNode choices = responseJson.get("choices");
                if (choices.isArray() && choices.size() > 0) {
                    String content = choices.get(0).get("message").get("content").asText();
                    // 응답 문자열을 배열로 변환
                    String[] responseArray = content.split("\\n");

                    // 배열을 문자열로 변환하여 반환
                    return String.join(", ", responseArray);
                } else {
                    return "No response from OpenAI API.";
                }
            } else {
                return "Error: " + response.statusCode() + " - " + response.body();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "An error occurred.";
        }
    }
}