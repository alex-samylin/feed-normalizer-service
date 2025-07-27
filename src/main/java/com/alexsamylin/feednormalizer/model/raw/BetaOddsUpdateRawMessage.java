package com.alexsamylin.feednormalizer.model.raw;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
public class BetaOddsUpdateRawMessage {
    private String type;

    @JsonProperty("event_id")
    private String eventId;

    private Map<String, Double> odds; // "home", "draw", "away"
}

