package com.eath.web;

import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
@CrossOrigin
@RestController
@RequestMapping("/webhook")
public class DialogflowController {

    private final ObjectMapper mapper = new ObjectMapper();

    @PostMapping
    @ResponseBody
    public String handleRequest(@RequestBody String requestBody) {
        try {
            JsonNode jsonNode = mapper.readTree(requestBody);

            // Extraire l'intent depuis la requête Dialogflow
            JsonNode queryResultNode = jsonNode.path("queryResult");
            String intent = queryResultNode.path("intent").path("displayName").asText();

            // Gérer l'intent et générer une réponse
            String responseText;
            switch (intent) {
                case "Default Welcome Intent":
                    responseText = "Welcome to the Google Assistant app. How can I assist you today?";
                    break;
                case "HelloWorldIntent":
                    responseText = "Hello World!";
                    break;
                default:
                    responseText = "I’m sorry, I didn’t understand that.";
            }

            // Créer et retourner la réponse Dialogflow
            return String.format("{\"fulfillmentText\": \"%s\"}", responseText);
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"fulfillmentText\": \"There was an error processing the request.\"}";
        }
    }
}
