package com.alexsamylin.feednormalizer.model.raw;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AlphaSettlementRawMessage {
    @JsonProperty("msg_type")
    private String msgType;

    @JsonProperty("event_id")
    private String eventId;

    private String outcome; // e.g., "1"
}
