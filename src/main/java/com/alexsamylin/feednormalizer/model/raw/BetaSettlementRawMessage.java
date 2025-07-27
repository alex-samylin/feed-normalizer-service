package com.alexsamylin.feednormalizer.model.raw;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BetaSettlementRawMessage {
    private String type;

    @JsonProperty("event_id")
    private String eventId;

    private String result;
}
