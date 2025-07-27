package com.alexsamylin.feednormalizer.controller;

import com.alexsamylin.feednormalizer.model.enums.Provider;
import com.alexsamylin.feednormalizer.service.FeedProcessingService;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class FeedController {

    private final FeedProcessingService feedProcessingService;

    @PostMapping("/provider-alpha/feed")
    public ResponseEntity<Void> receiveAlphaFeed(@RequestBody JsonNode message) {
        feedProcessingService.process(message, Provider.ALPHA);
        return ResponseEntity.accepted().build();
    }

    @PostMapping("/provider-beta/feed")
    public ResponseEntity<Void> receiveBetaFeed(@RequestBody JsonNode message) {
        feedProcessingService.process(message, Provider.BETA);
        return ResponseEntity.accepted().build();
    }
}
