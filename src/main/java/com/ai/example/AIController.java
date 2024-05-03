package com.ai.example;

import java.util.Map;

import org.springframework.ai.ollama.OllamaChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/ai")
public class AIController {

	@Autowired
    private OllamaChatClient client;

	@GetMapping("/generate")
    public Map generate(@RequestParam(value = "prompt", defaultValue = "Tell me a joke") String prompt) {
        return Map.of("generation", client.call(prompt));
    }
	
    @GetMapping("/prompt")
    public Flux<String> promptResponse(@RequestParam("prompt") String prompt) {
    
        Flux<String> response = client.stream(prompt);
        return response;

    }
    
}
