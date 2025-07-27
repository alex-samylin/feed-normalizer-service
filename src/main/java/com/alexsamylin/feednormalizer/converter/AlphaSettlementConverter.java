package com.alexsamylin.feednormalizer.converter;

import com.alexsamylin.feednormalizer.model.standard.StandardBetSettlement;
import com.alexsamylin.feednormalizer.model.enums.Outcome;
import com.alexsamylin.feednormalizer.model.raw.AlphaSettlementRawMessage;
import org.springframework.stereotype.Component;

@Component
public class AlphaSettlementConverter implements FeedConverter<AlphaSettlementRawMessage, StandardBetSettlement> {

    @Override
    public StandardBetSettlement convert(AlphaSettlementRawMessage raw) {
        return StandardBetSettlement.builder()
                .eventId(raw.getEventId())
                .outcome(switch (raw.getOutcome()) {
                    case "1" -> Outcome.HOME;
                    case "X" -> Outcome.DRAW;
                    case "2" -> Outcome.AWAY;
                    default -> throw new IllegalArgumentException("Unknown outcome: " + raw.getOutcome());
                })
                .build();
    }
}

