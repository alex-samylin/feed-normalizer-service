package com.alexsamylin.feednormalizer.service;


import com.alexsamylin.feednormalizer.converter.FeedConverter;
import com.alexsamylin.feednormalizer.model.enums.Provider;
import com.alexsamylin.feednormalizer.model.standard.StandardizedMessage;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FeedMessageHandler<I, O extends StandardizedMessage> {

    private final ObjectMapper objectMapper;
    private final Provider provider;
    private final String messageTypeKey;
    private final String expectedMessageType;
    private final Class<I> inputType;
    private final FeedConverter<I, O> converter;

    public FeedMessageHandler(
            ObjectMapper objectMapper,
            Provider provider,
            String messageTypeKey,
            String expectedMessageType,
            Class<I> inputType,
            FeedConverter<I, O> converter
    ) {
        this.objectMapper = objectMapper;
        this.provider = provider;
        this.messageTypeKey = messageTypeKey;
        this.expectedMessageType = expectedMessageType;
        this.inputType = inputType;
        this.converter = converter;
    }

    public boolean supports(Provider provider, JsonNode root) {
        return this.provider == provider &&
                expectedMessageType.equalsIgnoreCase(root.path(messageTypeKey).asText());
    }

    public StandardizedMessage handle(JsonNode root) {
        try {
            I raw = objectMapper.treeToValue(root, inputType);
            return converter.convert(raw);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse message for provider " + provider +
                    " and type " + expectedMessageType + ": " + e.getMessage(), e);
        }
    }
}
