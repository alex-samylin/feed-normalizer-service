package com.alexsamylin.feednormalizer.converter;

import com.alexsamylin.feednormalizer.model.standard.StandardOddsChange;
import com.alexsamylin.feednormalizer.model.raw.AlphaOddsUpdateRawMessage;
import org.springframework.stereotype.Component;

@Component
public class AlphaOddsConverter implements FeedConverter<AlphaOddsUpdateRawMessage, StandardOddsChange> {

    @Override
    public StandardOddsChange convert(AlphaOddsUpdateRawMessage raw) {
        return StandardOddsChange.builder()
                .eventId(raw.getEventId())
                .homeOdds(raw.getValues().getOrDefault("1", 0.0))
                .drawOdds(raw.getValues().getOrDefault("X", 0.0))
                .awayOdds(raw.getValues().getOrDefault("2", 0.0))
                .build();
    }
}
