package com.alexsamylin.feednormalizer.converter;

import com.alexsamylin.feednormalizer.model.standard.StandardOddsChange;
import com.alexsamylin.feednormalizer.model.raw.BetaOddsUpdateRawMessage;
import org.springframework.stereotype.Component;

@Component
public class BetaOddsConverter implements FeedConverter<BetaOddsUpdateRawMessage, StandardOddsChange> {

    @Override
    public StandardOddsChange convert(BetaOddsUpdateRawMessage raw) {
        return StandardOddsChange.builder()
                .eventId(raw.getEventId())
                .homeOdds(raw.getOdds().getOrDefault("home", 0.0))
                .drawOdds(raw.getOdds().getOrDefault("draw", 0.0))
                .awayOdds(raw.getOdds().getOrDefault("away", 0.0))
                .build();
    }
}
