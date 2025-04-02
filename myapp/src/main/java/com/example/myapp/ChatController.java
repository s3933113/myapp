package com.example.myapp;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

@RestController
public class ChatController {

    @PostMapping("/api/chat")
    public ResponseEntity<String> chat(@RequestBody Map<String, String> req) {
        String userMessage = req.get("message");

        try {
            // เตรียม JSON body
            String json = String.format("{\"message\": \"%s\"}", userMessage);

            // สร้าง HTTP request ไปที่ GPT-2 API
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:5000/generate"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            // ส่ง request
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // อ่านค่าที่ตอบกลับ
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(response.body());
            String reply = node.get("response").asText();

            return ResponseEntity.ok(reply);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("GPT-2 connection failed");
        }
    }
}
