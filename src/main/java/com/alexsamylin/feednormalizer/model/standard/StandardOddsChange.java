package com.alexsamylin.feednormalizer.model.standard;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StandardOddsChange implements StandardizedMessage {
    private String eventId;
    private double homeOdds;
    private double drawOdds;
    private double awayOdds;
}
