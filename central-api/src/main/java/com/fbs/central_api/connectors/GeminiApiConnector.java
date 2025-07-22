package com.fbs.central_api.connectors;

import com.fbs.central_api.models.GeminiApiRequest;
import com.fbs.central_api.models.GeminiApiResponse;
import com.fbs.central_api.models.RequestContent;
import com.fbs.central_api.models.RequestPart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class GeminiApiConnector {
    RestTemplate restTemplate;

    @Autowired
    public GeminiApiConnector(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${gemini.genai.url}")
    String geminiApiUrl;

    @Value("${gemini.token}")
    String geminiApiToken;

    String geminiApiTokenHeader = "X-goog-api-key";

    public GeminiApiResponse callGeminiGenAiEndpoint(String prompt) {
        // Creation of the request body
        GeminiApiRequest geminiApiRequestBody = new GeminiApiRequest();
        RequestPart requestPart = new RequestPart();
        requestPart.setText(prompt);
        List<RequestPart> requestParts = new ArrayList<>();
        requestParts.add(requestPart);
        RequestContent requestContent = new RequestContent();
        requestContent.setParts(requestParts);
        List<RequestContent> requestContents = new ArrayList<>();
        requestContents.add(requestContent);
        geminiApiRequestBody.setContents(requestContents);
        HttpHeaders headers = new HttpHeaders();
        headers.add(geminiApiTokenHeader, geminiApiToken);
        headers.add("Content-Type", "application/json");
        HttpEntity<GeminiApiRequest> httpEntity = new HttpEntity<>(geminiApiRequestBody, headers);
        ResponseEntity<GeminiApiResponse> response = restTemplate.exchange(geminiApiUrl, HttpMethod.POST, httpEntity, GeminiApiResponse.class);
        return response.getBody();
    }
}
