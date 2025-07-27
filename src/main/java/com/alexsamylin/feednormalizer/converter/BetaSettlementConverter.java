package com.alexsamylin.feednormalizer.converter;

import com.alexsamylin.feednormalizer.model.standard.StandardBetSettlement;
import com.alexsamylin.feednormalizer.model.enums.Outcome;
import com.alexsamylin.feednormalizer.model.raw.BetaSettlementRawMessage;
import org.springframework.stereotype.Component;

@Component
public class BetaSettlementConverter implements FeedConverter<BetaSettlementRawMessage, StandardBetSettlement> {

    @Override
    public StandardBetSettlement convert(BetaSettlementRawMessage raw) {
        return StandardBetSettlement.builder()
                .eventId(raw.getEventId())
                .outcome(switch (raw.getResult().toLowerCase()) {
                    case "home" -> Outcome.HOME;
                    case "draw" -> Outcome.DRAW;
                    case "away" -> Outcome.AWAY;
                    default -> throw new IllegalArgumentException("Unknown result: " + raw.getResult());
                })
                .build();
    }
}
