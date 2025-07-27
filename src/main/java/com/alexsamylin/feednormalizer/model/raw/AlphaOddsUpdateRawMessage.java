package com.alexsamylin.feednormalizer.model.raw;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
public class AlphaOddsUpdateRawMessage {
    @JsonProperty("msg_type")
    private String msgType;

    @JsonProperty("event_id")
    private String eventId;

    private Map<String, Double> values; // "1", "X", "2"
}

