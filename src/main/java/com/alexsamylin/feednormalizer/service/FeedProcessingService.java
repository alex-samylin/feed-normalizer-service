package com.alexsamylin.feednormalizer.service;

import com.alexsamylin.feednormalizer.model.standard.StandardizedMessage;
import com.alexsamylin.feednormalizer.model.enums.Provider;
import com.alexsamylin.feednormalizer.publisher.StandardMessagePublisher;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FeedProcessingService {

    private final List<FeedMessageHandler<?, ?>> handlers;
    private final StandardMessagePublisher publisher;

    public void process(JsonNode root, Provider provider) {
        FeedMessageHandler<?, ?> handler = handlers.stream()
                .filter(h -> h.supports(provider, root))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unsupported message for provider: " + provider));

        StandardizedMessage msg = handler.handle(root);
        publisher.publishMessage(msg);
    }

}
