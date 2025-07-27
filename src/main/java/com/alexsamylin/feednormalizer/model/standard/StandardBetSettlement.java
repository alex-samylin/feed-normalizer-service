package com.alexsamylin.feednormalizer.model.standard;

import com.alexsamylin.feednormalizer.model.enums.Outcome;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StandardBetSettlement implements StandardizedMessage {
    private String eventId;
    private Outcome outcome;
}
